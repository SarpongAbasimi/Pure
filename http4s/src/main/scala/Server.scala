import cats.effect.{ConcurrentEffect, Sync, Timer}
import org.http4s.server.blaze.BlazeServerBuilder
import org.http4s.client._
import org.http4s.client.blaze._
import cats.implicits._
import scala.concurrent.ExecutionContext.global
import org.http4s.implicits._

object Server {

  def stream[F[_] : ConcurrentEffect](implicit time: Timer[F]) = {
    for {
      client <- BlazeClientBuilder[F](global).stream
      applicationRoutes = (
        Routes.index[F](client) <+>
          Routes.helloName[F]   <+>
          Routes.postRequest[F] <+>
          Routes.makeR[F]
        ).orNotFound

      exitCode <- BlazeServerBuilder[F](global)
        .bindHttp(9010, "localhost")
        .withHttpApp(applicationRoutes)
        .serve
    } yield exitCode
  }.drain
}