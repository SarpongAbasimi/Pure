import cats._
import cats.effect._
import cats.effect.implicits._
import cats.implicits._
import fs2.Stream
import scala.concurrent.Future
import scala.concurrent.duration.{FiniteDuration, HOURS, MILLISECONDS, NANOSECONDS}

object Learn extends IOApp {
  def run(args: List[String]): IO[ExitCode] = {
    IO{
      IO {
        Stream[fs2.Pure, Int] = Stream(1,2,3)
      }.unsafeRunSync()
    }.as(ExitCode.Success)
  }

  def multiple(a: Int, b: Int): Int = a * b

  def greet(name: String) : IO[String] = {
    val userInput: IO[String] = Applicative[IO].pure[String](scala.io.StdIn.readLine())
    for {
      name <- userInput
      greet <- IO(s"Hello, ${name} how are you doing today?")
    } yield (greet)
  }

  def summation(n: Int): IO[Int] = {
    val numbers = Stream.iterate(0)((number) => number |+| 1).take(n)
    Foldable[List].fold(numbers.toList).pure[IO]
  }
//  def traverse[G[_]: Applicative, A, B](fa: F[A])(f: A => G[B]): G[F[B]]
  def traverse(data: Stream[fs2.Pure ,Int]) = {
    Traverse[List].traverse[Option, Int, Int](data.toList)((eachElementInData) => Option(eachElementInData))
  }
}
