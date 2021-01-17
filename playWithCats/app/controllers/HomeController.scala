package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import Services.TodoService
import scala.concurrent.ExecutionContext.Implicits.global
import  Forms._
/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(cc: ControllerComponents, todoService: TodoService, messagesAction: MessagesActionBuilder) extends AbstractController(cc) {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action.async { implicit request: Request[AnyContent] =>
    todoService.all.map(listOfToDos => {
      Ok(views.html.index(listOfToDos))
    })
  }

  def create = messagesAction { implicit request: MessagesRequest[AnyContent] =>
    Ok(views.html.create(AppForms.todoForm))
  }

  def created: Action[AnyContent] = messagesAction { implicit request:  MessagesRequest[AnyContent] =>
    AppForms.todoForm.bindFromRequest.fold( hasErrors => {
      BadRequest(views.html.create(hasErrors))
    }, todoData => {
      todoService.create(todoData.todo)
      Redirect(routes.HomeController.index()).flashing("success" -> "The item has been created")
    })
  }
}
