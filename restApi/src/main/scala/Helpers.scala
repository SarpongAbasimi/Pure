import cats.effect._
import doobie._
import doobie.implicits._

final case class Todo(id: Int, todo: String)

object TodoHelpers {
  def getTodos[F[_]](transcator: Transactor.Aux[F, Unit])(implicit b: Bracket[F, Throwable]) = {
    sql"select id, name from todos"
      .query[Todo]
      .stream
      .compile
      .toList
      .transact(transcator)
  }
}
