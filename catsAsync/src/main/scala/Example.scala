import cats.effect.IO
import cats.effect._
import scala.concurrent.Future
import cats.effect.ContextShift
import scala.util.{Failure, Success}
import cats.Eq
import cats.implicits._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Try

case class Car(model: String, price: Float)
object Example {
  def main(args: Array[String]): Unit = {
    val firstCar = Car("Benz", 77)
    val secondCar = Car("Benz", 90)
    implicit val eqInstanceForCar: Eq[Car] = new Eq[Car] {
      def eqv(x: Car, y: Car): Boolean = Eq[String].eqv(x.model, y.model)
    }
    val carComparison = Eq[Car].eqv(firstCar, secondCar)

    println(carComparison)
  }
}
