// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/chrissongz/Desktop/sca/playWithCats/conf/routes
// @DATE:Sat Jan 16 18:04:57 GMT 2021


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
