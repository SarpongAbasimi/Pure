import cats._
import cats.effect._
import cats.effect.implicits._
import cats.implicits._
import fs2.Stream
import scodec.bits.ByteVector.Chunk

import scala.concurrent.Future
import scala.concurrent.duration.{FiniteDuration, HOURS, MILLISECONDS, NANOSECONDS}

sealed trait TrafficLight
final case object Red extends TrafficLight
final case object Yellow extends TrafficLight
final case object Green extends  TrafficLight

sealed trait Calculation
final case class Success(result: Int) extends Calculation
final case class Failure(message: String) extends Calculation

//ADT with Polymorphism
sealed trait A {
  def foo: String = "It is an A"
}

final case object B extends A {
  override def  foo: String = "It is B!"
}

final case object C extends A {
  override def foo: String = "It's C!"
}

// Product type pattern match
final case class N(b: String, c: String) {
  def f(a: N): String = ???
}


object Learn extends IOApp {
  def run(args: List[String]): IO[ExitCode] = {
    IO{
      IO {
        val anA: A = B
        println(anA)
        println(anA.foo)
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

  def traverse(data: Stream[fs2.Pure ,Int]) = {
    Traverse[List].traverse[Option, Int, Int](data.toList)((eachElementInData) => Option(eachElementInData))
  }

  def average(someData: Stream[fs2.Pure, Int]): Int = {
    Foldable[List].foldLeft(someData.toList, 0)((a, b) => (a + b)) / someData.toList.length
  }
  val effectfulStream: Stream[IO, Unit] = Stream.eval[IO, Unit](IO(println("Did this work")))
  val convertEffectFullStreamToList = effectfulStream.compile.toList
  val combineStreams = Stream(1,2,3,4) ++ Stream(2,4,5)

  // Variance
//  sealed trait Maybe[+A]
//  final case class Full[A](value: A) extends Maybe[A]
//  case object Empty extends  Maybe[Nothing]

}
