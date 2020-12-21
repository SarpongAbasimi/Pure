import cats.effect._
import doobie._
import doobie.implicits._
import Encoders.Todo

object TodoHelpers {
  def getTodos[F[_]](transcator: Transactor.Aux[F, Unit])(implicit b: Bracket[F, Throwable]) = {
    sql"select id, todo from todos"
      .query[Todo]
      .stream
      .compile
      .toList
      .transact(transcator)
  }

  def getTodoByID[F[_]](id: Int, transcator: Transactor.Aux[F, Unit])(implicit b: Bracket[F, Throwable]) = {
    sql"select * from todos where id=$id"
      .query[Todo]
      .unique
      .transact(transcator)
  }

  def createTodo[F[_]](todo: Todo, transcator: Transactor.Aux[F, Unit])(implicit b: Bracket[F, Throwable]) = {
    sql"insert into todos(id,todo) values(${todo.id}, ${todo.todo})"
      .update
      .run
      .transact(transcator)
  }
}
