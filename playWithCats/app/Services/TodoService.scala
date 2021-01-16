package Services
import java.sql.Connection

import javax.inject._
import play.api.db.Database
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import anorm._
import model._

trait TodoServices {
  def create(todo: String): Future[Option[Long]]
  def all: Future[List[Todo]]
}

object Parsers {
  val TodoParser: RowParser[Todo] = Macro.namedParser[Todo]
}

@Singleton
class TodoService @Inject()(db: Database) extends TodoServices {
  def create(aTodo: String) = Future {
    db.withConnection { implicit connection: Connection =>
      SQL(s"insert into Todo(todo) values ($aTodo)")
        .executeInsert()
      }
  }

  def all = Future {
    db.withConnection { implicit connection: Connection =>
      SQL("select * from Todo")
        .as[List[Todo]](Parsers.TodoParser.*)
    }
  }

}
