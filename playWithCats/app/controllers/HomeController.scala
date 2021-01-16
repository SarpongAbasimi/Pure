package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import Services.TodoService
import scala.concurrent.ExecutionContext.Implicits.global
/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(cc: ControllerComponents, todoService: TodoService) extends AbstractController(cc) {

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

}
