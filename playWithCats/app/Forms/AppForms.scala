package Forms

import play.api.data.{Form, _}
import play.api.data.Forms.{mapping, nonEmptyText, _}
import play.api.data.validation.Constraints._
import model.Todo

object AppForms {
  val todoForm: Form[Todo] = Form(
    mapping(
      "todo" -> text.verifying(nonEmpty)
    )(Todo.apply)(Todo.unapply)
  )
}


