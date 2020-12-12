name := "doobie"

version := "0.1"

scalaVersion := "2.13.4"

lazy val doobieVersion = "0.9.0"

libraryDependencies ++= Seq(
  "org.tpolecat" %% "doobie-core"     % doobieVersion,
  "org.tpolecat" %% "doobie-postgres" % doobieVersion,
  "org.tpolecat" %% "doobie-specs2"   % doobieVersion,
)