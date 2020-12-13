import doobie._
import doobie.implicits._
import cats._
import cats.effect._
import cats.implicits._


object Main {
  def main(args: Array[String]): Unit = {
    implicit val cs = IO.contextShift(ExecutionContexts.synchronous)
    val progaram1: ConnectionIO[Int] = 42.pure[ConnectionIO]

    val xa:Transactor.Aux[IO, Unit] = Transactor.fromDriverManager[IO](
      "org.postgresql.Driver",     // driver classname
      "jdbc:postgresql:chrissongz",     // connect URL (driver-specific)
      "chrissongz",                  // user
      "",                          // password
      Blocker.liftExecutionContext(ExecutionContexts.synchronous) // just for testing
    )

    val createTable: IO[ConnectionIO[Int]]= IO(sql"create table animal(id int primary key not null, name text not null)".update.run)
    val insertDataIntoAnimal: IO[ConnectionIO[Int]] = IO(sql"insert into animal(id name) values(1, bam)".update.run)


    val myQuery = sql"select name from person"
      .query[String]
      .stream.compile
      .toList
      .transact(xa)
      .unsafeRunSync()
      .foreach(println)

  }
}
