
package views.html

import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import play.api.mvc._
import play.api.data._

object index extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[List[model.Todo],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(todos: List[model.Todo]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.27*/("""

"""),_display_(/*3.2*/main("Welcome")/*3.17*/ {_display_(Seq[Any](format.raw/*3.19*/("""
  """),_display_(/*4.4*/for(todo <- todos) yield /*4.22*/{_display_(Seq[Any](format.raw/*4.23*/("""
    """),format.raw/*5.5*/("""<div>
      <p>"""),_display_(/*6.11*/todo/*6.15*/.todo),format.raw/*6.20*/("""</p>
    </div>
  """)))}),format.raw/*8.4*/("""
  """),format.raw/*9.3*/("""<a href=""""),_display_(/*9.13*/routes/*9.19*/.HomeController.create),format.raw/*9.41*/("""">
    <span>Create todo</span>
  </a>
""")))}))
      }
    }
  }

  def render(todos:List[model.Todo]): play.twirl.api.HtmlFormat.Appendable = apply(todos)

  def f:((List[model.Todo]) => play.twirl.api.HtmlFormat.Appendable) = (todos) => apply(todos)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: 2021-01-17T12:45:09.223369
                  SOURCE: /Users/chrissongz/Desktop/sca/playWithCats/app/views/index.scala.html
                  HASH: bdb090fbaa48d1bde768eb9763e14bca26e98cf8
                  MATRIX: 739->1|859->26|889->31|912->46|951->48|981->53|1014->71|1052->72|1084->78|1127->95|1139->99|1164->104|1214->125|1244->129|1280->139|1294->145|1336->167
                  LINES: 21->1|26->1|28->3|28->3|28->3|29->4|29->4|29->4|30->5|31->6|31->6|31->6|33->8|34->9|34->9|34->9|34->9
                  -- GENERATED --
              */
          