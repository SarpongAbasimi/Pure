import cats.effect._
import org.http4s._
import org.http4s.dsl.Http4sDsl
import io.circe._
import org.http4s.circe._
import org.http4s.client.Client
import org.http4s.client.dsl.Http4sClientDsl

case class Movie(title: String, Rating: Int, Lang: String)
case class Data(id: String, title: String)

object Routes {

  def index[F[_] : Sync](client: Client[F]): HttpRoutes[F] = {
    val dsl = new Http4sDsl[F] {}
    val something = client.expect[String]("https://jsonplaceholder.typicode.com/todos/1")
    import dsl._
    HttpRoutes.of[F]{
      case GET -> Root => {
        Ok(something)
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

  def routeWithClient[F[_]: Sync](client: Client[F]) = {
    val dsl = new Http4sClientDsl[F]{}
    client.expect[String]("https://jsonplaceholder.typicode.com/todos/1")
  }

}
