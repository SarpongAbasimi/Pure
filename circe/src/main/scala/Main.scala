import io.circe._
import io.circe.parser._
import io.circe.syntax._
import io.circe.generic.semiauto._
import io.circe.generic.auto._

case class Movie(name:String, age:Int)

object Main {
  def main(args: Array[String]): Unit = {
    val someList = List(1,2,3,4)
    val encodeToJson = someList.asJson
    val decodeJsonToList = encodeToJson.as[List[Int]]
//    implicit val movieEncoder: Encoder[Movie] = deriveEncoder[Movie]
    println(Movie("Avengers", 3).asJson)


//    print(encodeToJson)
//    println(decodeJsonToList)
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
