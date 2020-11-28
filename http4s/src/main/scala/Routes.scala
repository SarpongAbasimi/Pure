import cats.effect._
import org.http4s._
import org.http4s.dsl.Http4sDsl

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
}
