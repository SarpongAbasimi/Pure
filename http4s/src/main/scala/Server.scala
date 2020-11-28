import cats.effect.{ConcurrentEffect, Sync, Timer}
import org.http4s.server.blaze.BlazeServerBuilder
import cats.implicits._
import scala.concurrent.ExecutionContext.global
import org.http4s.implicits._

object Server {

  def stream[F[_] : ConcurrentEffect](implicit time: Timer[F]) = {
    val applicationRoutes = (
      Routes.index[F]       <+>
      Routes.helloName[F]   <+>
      Routes.postRequest[F] <+>
      Routes.dealingWithJson[F]
      ).orNotFound

    BlazeServerBuilder[F](global)
      .bindHttp(9010, "localhost")
      .withHttpApp(applicationRoutes)
      .serve
  }
}