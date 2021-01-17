
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

object index extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[List[model.Todo],Flash,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(todos: List[model.Todo])(implicit flash: Flash):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.50*/("""

"""),_display_(/*3.2*/main("Welcome")/*3.17*/ {_display_(Seq[Any](format.raw/*3.19*/("""
  """),format.raw/*4.3*/("""<div>"""),_display_(/*4.9*/flash/*4.14*/.get("success").getOrElse("Welcome!")),format.raw/*4.51*/("""</div>
  """),_display_(/*5.4*/for(todo <- todos) yield /*5.22*/{_display_(Seq[Any](format.raw/*5.23*/("""
    """),format.raw/*6.5*/("""<div>
      <p>"""),_display_(/*7.11*/todo/*7.15*/.todo),format.raw/*7.20*/("""</p>
    </div>
  """)))}),format.raw/*9.4*/("""
  """),format.raw/*10.3*/("""<a href=""""),_display_(/*10.13*/routes/*10.19*/.HomeController.create),format.raw/*10.41*/("""">
    <span>Create todo</span>
  </a>
""")))}))
      }
    }
  }

  def render(todos:List[model.Todo],flash:Flash): play.twirl.api.HtmlFormat.Appendable = apply(todos)(flash)

  def f:((List[model.Todo]) => (Flash) => play.twirl.api.HtmlFormat.Appendable) = (todos) => (flash) => apply(todos)(flash)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: 2021-01-17T18:32:34.652425
                  SOURCE: /Users/chrissongz/Desktop/sca/playWithCats/app/views/index.scala.html
                  HASH: b3c609c044cddf9d3fdd989a476d91f82302eeec
                  MATRIX: 745->1|888->49|918->54|941->69|980->71|1010->75|1041->81|1054->86|1111->123|1147->134|1180->152|1218->153|1250->159|1293->176|1305->180|1330->185|1380->206|1411->210|1448->220|1463->226|1506->248
                  LINES: 21->1|26->1|28->3|28->3|28->3|29->4|29->4|29->4|29->4|30->5|30->5|30->5|31->6|32->7|32->7|32->7|34->9|35->10|35->10|35->10|35->10
                  -- GENERATED --
              */
          