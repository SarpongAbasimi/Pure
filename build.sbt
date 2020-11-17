name := "sca"

version := "0.1"

scalaVersion := "2.13.3"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % "2.1.1",
  "org.typelevel" %% "cats-effect" % "2.2.0",
  "co.fs2" %% "fs2-core" % "2.4.4"
  )