import cats.effect._
import org.http4s._
import org.http4s.dsl.Http4sDsl
import io.circe.Json
import io.circe.literal._
import org.http4s.circe._
import io.circe.generic.auto._
import io.circe.syntax._

case class Movie(title: String, Rating: Int, Lang: String)
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

  def dealingWithJson[F[_]: Sync]: HttpRoutes[F] = {
    val dsl = new Http4sDsl[F] {}
    import  dsl._
    def hello(name: String): Json = json"""{"hello": $name}"""
    HttpRoutes.of[F]{
      case GET -> Root/ "api"/ lang=> {
        Ok(Movie("Batman", 1, lang).asJson)
      }
    }
  }
}
