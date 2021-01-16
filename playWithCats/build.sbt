lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := """play-scala-hello-world-tutorial""",
    organization := "com.example",
    version := "1.0-SNAPSHOT",
    scalaVersion := "2.13.1",
    libraryDependencies ++= Seq(
      guice,
      jdbc,
      evolutions,
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test,
      "org.typelevel" %% "cats-core" % "2.1.1",
      "org.postgresql" % "postgresql" % "42.2.18",
      "org.playframework.anorm" %% "anorm" % "2.6.7"
    ),
    scalacOptions ++= Seq(
      "-feature",
      "-deprecation",
      "-Xfatal-warnings"
    )
  )
