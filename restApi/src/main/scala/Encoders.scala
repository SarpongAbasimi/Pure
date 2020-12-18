import cats.Applicative
import io.circe._
import org.http4s.circe._
import org.http4s.EntityEncoder

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
}
