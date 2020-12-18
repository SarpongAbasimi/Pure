import org.http4s._
import org.http4s.dsl.Http4sDsl
import cats.effect._
import doobie._
import io.circe._
import Encoders._
import org.http4s.circe.jsonEncoderOf

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

  def show[F[_]](transactor: Transactor.Aux[F, Unit] )(implicit sync: Sync[F]) = {
    val dsl = new Http4sDsl[F] {}
    import dsl._

    HttpRoutes.of[F]{
      case GET ->  Root/"todos"/IntVar(id) => {
        val todo = TodoHelpers.getTodoByID(id,transactor)
        Ok(todo)
      }
    }
  }
}
