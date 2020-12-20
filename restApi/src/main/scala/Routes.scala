import org.http4s._
import org.http4s.dsl.Http4sDsl
import cats.effect._
import doobie._
import Encoders._
import Decoders._
import circe._
import cats.syntax.all._

object Routes {

  def index[F[_]](transactor: Transactor.Aux[F, Unit] )(implicit sync: Sync[F]) = {
    val dsl = new Http4sDsl[F] {}
    import dsl._
    HttpRoutes.of[F]{
      case GET -> Root => {
        val todos = TodoHelpers.getTodos(transactor)
        Ok("Hey")
      }
    }
  }

  def show[F[_]](transactor: Transactor.Aux[F, Unit])(implicit sync: Sync[F]) = {
    val dsl = new Http4sDsl[F] {}
    import dsl._

    HttpRoutes.of[F]{
      case GET -> Root/IntVar(id) => {
        val todo = TodoHelpers.getTodoByID(id,transactor)
        Ok(todo)
      }
    }
  }

  def create[F[_]](transactor: Transactor.Aux[F, Unit])(implicit sync: Sync[F]): HttpRoutes[F] = {
    val dsl = new Http4sDsl[F] {}
    import dsl._

    HttpRoutes.of[F]{
      case req @ POST -> Root/ create => {
        val result: F[Todo] = req.decodeJson[Todo]
        result.flatMap(todo => TodoHelpers.createTodo(todo, transactor)).flatMap(_ => Ok())
      }
    }
  }
}
