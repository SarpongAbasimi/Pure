import io.circe._
import io.circe.parser._
import io.circe.syntax._
import io.circe.generic.semiauto._
//import io.circe.generic.auto._

case class Movie(name:String, age:Int)

object Main {
  object EncodersAndDecoders {
    implicit val movieEncoder = new Encoder[Movie] {
      final def apply(a: Movie): Json = {
        val json = s"""{
          |"name":"${a.name}",
          |"age":"${a.age}"
          |}""".stripMargin
        parse(json).getOrElse(Json.Null)
      }
    }

    implicit val movieDecoder = new Decoder[Movie] {
      final def apply(c: HCursor): Decoder.Result[Movie] = {
        for {
          name <- c.downField("name").as[String]
          age <- c.downField("age").as[Int]
        } yield Movie(name, age)
      }
    }
  }
  import EncodersAndDecoders._

  def main(args: Array[String]): Unit = {
    val encoderedMovie = Movie("a", 1).asJson
    val decodedMovie = encoderedMovie.as[Movie]
    println(decodedMovie)
  }

  def someRandomJson = {
    val someJson =
      """
        |{
        |"name": "chris",
        |"country": "SomeCountry",
        |"age" : "someAge",
        |"listOfItems": ["orange", "apple", "cake"]
        |}
        |""".stripMargin

    parse(someJson) match {
      case Left(failure) => println(s"There was an error $failure")
      case Right(json) => println(s"success \n $json")
    }
  }

  def anotherJson = {
    val someJson =
      """
        |{
        |"name": "chris",
        |"country": "SomeCountry",
        |"age" : "someAge",
        |"listOfItems": ["orange", "apple", "cake"],
        |"values" : {
        |"mango": "5 cedis",
        |"fufu": "30 cedis",
        |"ankaa": "10 cedis",
        |"nam": ["koobi", "momoni", "akantie"]
        | }
        |}
        |""".stripMargin

    val json = parse(someJson).getOrElse(Json.Null)
    val cursor: HCursor = json.hcursor
    val theFieldIWantName : Decoder.Result[String] = cursor.downField("name").as[String]
    val listOfItems : Decoder.Result[List[String]] = cursor.downField("listOfItems").as[List[String]]
    val valuesMango : Decoder.Result[String] = cursor.downField("values").downField("mango").as[String]
    val valuesNam : Decoder.Result[Seq[String]] = cursor.downField("values").downField("nam").as[Seq[String]]
    val valuesNamKoobi : Decoder.Result[String] = cursor.downField("values").downField("nam").downArray.as[String]

    println(theFieldIWantName)
    println(listOfItems)
    println(valuesMango)
    println(valuesNam)
    println(valuesNamKoobi)
  }
}
