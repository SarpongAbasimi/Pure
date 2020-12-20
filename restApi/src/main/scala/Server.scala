import cats.effect._
import org.http4s.server.blaze.BlazeServerBuilder
import scala.concurrent.ExecutionContext.global
import cats.implicits._
import org.http4s.implicits._
import DB._

object Server {
  def stream[F[_]: ConcurrentEffect: Timer: ContextShift] = {

    val httpApp = (
      Routes.index[F](transactor[F]) <+>
      Routes.show[F](transactor[F]) <+>
      Routes.create[F](transactor[F])
      ).orNotFound
    BlazeServerBuilder[F](global).
      bindHttp(9010, "localhost")
      .withHttpApp(httpApp)
      .serve
  }
}
