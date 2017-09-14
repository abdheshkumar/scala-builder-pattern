name := "scala-basic"

version := "1.0"

scalaVersion := "2.12.1"

libraryDependencies ++=Seq(
  "org.scalatest" % "scalatest_2.12" % "3.0.3" % "test",
  "com.typesafe" % "config" % "1.3.1"
)