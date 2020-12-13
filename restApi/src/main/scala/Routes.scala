import org.http4s._
import org.http4s.implicits._
import cats._
import org.http4s.dsl.Http4sDsl
import cats.effect._
import doobie._
import doobie.implicits._

object Routes {

  def index[F[_]](transactor: Transactor.Aux[F, Unit] )(implicit sync: Sync[F]) = {
    val dsl = new Http4sDsl[F] {}
    import dsl._

    HttpRoutes.of[F]{
      case GET ->  Root => {
        val todos = TodoHelpers.getTodos(transactor)
        Ok("Hey")
      }
    }
  }
}
