import doobie._
import cats.effect._

object DB {
  def transactor[F[_] :ContextShift : Async] = {
    Transactor.fromDriverManager[F](
      "org.postgresql.Driver",
      "jdbc:postgresql:chrissongz",
      "chrissongz",
      "",
      Blocker.liftExecutionContext(ExecutionContexts.synchronous) // just for testing
    )
  }
}
