import sbt._

organization := "io.shedin.library"

name := "shedin-crud-lib"

version := "0.1.0-SNAPSHOT"

lazy val shedinCrudLib = project.in(file("."))
  .settings(name := "shedin-crud-lib")
  .settings(scalaVersion := "2.11.8")
  .settings(libraryDependencies ++= Seq("org.scala-lang" % "scala-library" % "2.11.8"))
  .settings(scalacOptions ++= Seq("-deprecation", "-feature"))
  .settings(ivyScala := ivyScala.value map {
    _.copy(overrideScalaVersion = true)
  })
