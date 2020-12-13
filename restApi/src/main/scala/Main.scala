import  cats.effect._
import Server._

object Main extends IOApp {
  override def run(args: List[String]): IO[ExitCode] = {
    stream[IO].compile.drain.as(ExitCode.Success)
  }
}
