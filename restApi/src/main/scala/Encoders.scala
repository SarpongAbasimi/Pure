import cats.Applicative
import io.circe.Decoder.Result
import io.circe._
import org.http4s.circe._
import org.http4s.EntityEncoder
import cats.effect.Sync
import io.circe.syntax._

object Encoders {
  final case class Todo(id: Int, todo: String)
  implicit val todoEncoder: Encoder[Todo] = new Encoder[Todo] {
    final def apply(a: Todo): Json = {
      Json.obj(
        ("id", Json.fromInt(a.id)),
        ("todo", Json.fromString(a.todo))
      )
    }
  }
  implicit def todoEntityEncoder[F[_]: Applicative]: EntityEncoder[F, Todo] = jsonEncoderOf[F, Todo]

  final case class Name(name:String)
  implicit val nameEncoder = new Encoder[Name] {
    final def apply(userName: Name): Json = Json.obj(("name", Json.fromString(userName.name)))
  }
  implicit def nameEntityEncoder[F[_]] = jsonEncoderOf[F, Name]

  implicit val listOfTodosEncoder: Encoder[List[Todo]] = new Encoder[List[Todo]] {
    final def apply(a: List[Todo]): Json = {
      ???
    }
  }
  implicit def listOfTodosEntityEncoder[F[_]] = jsonEncoderOf[F, List[Todo]]
}

object Decoders {
  import  Encoders.Todo

  implicit val todoDecoder: Decoder[Todo] = new Decoder[Todo] {
    final def apply(c: HCursor): Result[Todo] = {
      for {
        id <- c.downField("id").as[Int]
        todo <- c.downField("todo").as[String]
      } yield Todo(id, todo)
    }
  }

  implicit def todoEntityDecoder[F[_]: Sync] = jsonOf[F, Todo]
}
