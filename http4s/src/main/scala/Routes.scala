import cats.effect._
import org.http4s._
import org.http4s.dsl.Http4sDsl
import io.circe._
import io.circe.literal._
import org.http4s.circe._
//import io.circe.generic.auto._
import io.circe.syntax._

case class Movie(title: String, Rating: Int, Lang: String)
case class Data(id: String, title: String)

object Routes {
  def index[F[_] : Sync]: HttpRoutes[F] = {
    val dsl = new Http4sDsl[F] {}
    import dsl._
    HttpRoutes.of[F]{
      case GET -> Root => {
        Ok("Hello")
      }
    }
  }

  def helloName[F[_]: Sync]: HttpRoutes[F] = {
    val dsl = new Http4sDsl[F] {}
    import dsl._
    HttpRoutes.of[F]{
      case GET -> Root/ name => {
        Ok(s"Hello $name")
      }
    }
  }

  def postRequest[F[_] : Sync]: HttpRoutes[F] = {
    val dsl = new Http4sDsl[F] {}
    import  dsl._
    HttpRoutes.of[F]{
      case req @ POST -> Root => {
        Ok(req.body)
      }
    }
  }

//  def dealingWithJson[F[_]: Sync]: HttpRoutes[F] = {
//    val dsl = new Http4sDsl[F] {}
//    import  dsl._
//    def hello(name: String): Json = json"""{"hello": $name}"""
//    HttpRoutes.of[F]{
//      case GET -> Root/ "api"/ lang=> {
//        Ok(Movie("Batman", 1, lang).asJson)
//      }
//    }
//  }

  def makeR[F[_]: Sync]: HttpRoutes[F] = {
    val dsl = new Http4sDsl[F] {}
    import  dsl._

    implicit val movieEncoder: Encoder[Movie] = new Encoder[Movie] {
      final def apply(movie: Movie): Json = {
        (Json.obj(
          ("title", Json.fromString(movie.title)),
          ("rating", Json.fromInt(movie.Rating)),
          ("lang", Json.fromString(movie.Lang))
        ))
      }
    }
//    implicit val listOnMovieEncoder: Encoder[Seq[Movie]] = new Encoder[Seq[Movie]] {
//      final def apply(a: Seq[Movie]): Json = a.map(a => Json.obj(
//        ("title", Json.fromString(a.title)),
//        ("rating", Json.fromInt(a.Rating)),
//        ("lang", Json.fromString(a.Lang))
//      ))
//    }
    implicit val movieEntityEncoder: EntityEncoder[F, Movie] = jsonEncoderOf[F, Movie]
    HttpRoutes.of[F]{
      case GET -> Root/ "api"/ "l"/"m"=> {
        val c = List("nananana")
        Ok(Movie("hey", 1, "ha"))
      }
    }
  }
}
