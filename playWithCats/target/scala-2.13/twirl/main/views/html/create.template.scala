
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
/*1.2*/import helper._

object create extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[Form[model.Todo],MessagesRequestHeader,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*3.2*/(todoForm: Form[model.Todo])(implicit request: MessagesRequestHeader):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*4.1*/("""
"""),_display_(/*5.2*/main("Create")/*5.16*/{_display_(Seq[Any](format.raw/*5.17*/("""
    """),_display_(/*6.6*/helper/*6.12*/.form(action = routes.HomeController.created())/*6.59*/{_display_(Seq[Any](format.raw/*6.60*/("""
        """),_display_(/*7.10*/CSRF/*7.14*/.formField),format.raw/*7.24*/("""
        """),_display_(/*8.10*/helper/*8.16*/.inputText(todoForm("todo"))),format.raw/*8.44*/("""
    """),format.raw/*9.5*/("""<input type="submit" value="Submit">
    """)))}),format.raw/*10.6*/("""
    """),format.raw/*11.5*/("""<a href=""""),_display_(/*11.15*/routes/*11.21*/.HomeController.index),format.raw/*11.42*/("""">
        <span>
            < back
        </span>
    </a>
""")))}))
      }
    }
  }

  def render(todoForm:Form[model.Todo],request:MessagesRequestHeader): play.twirl.api.HtmlFormat.Appendable = apply(todoForm)(request)

  def f:((Form[model.Todo]) => (MessagesRequestHeader) => play.twirl.api.HtmlFormat.Appendable) = (todoForm) => (request) => apply(todoForm)(request)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: 2021-01-17T14:04:12.965855
                  SOURCE: /Users/chrissongz/Desktop/sca/playWithCats/app/views/create.scala.html
                  HASH: 97e5a1eede12a1f39996f0b818aaafea5c83e27f
                  MATRIX: 432->1|785->19|948->89|975->91|997->105|1035->106|1066->112|1080->118|1135->165|1173->166|1209->176|1221->180|1251->190|1287->200|1301->206|1349->234|1380->239|1452->281|1484->286|1521->296|1536->302|1578->323
                  LINES: 17->1|22->3|27->4|28->5|28->5|28->5|29->6|29->6|29->6|29->6|30->7|30->7|30->7|31->8|31->8|31->8|32->9|33->10|34->11|34->11|34->11|34->11
                  -- GENERATED --
              */
          