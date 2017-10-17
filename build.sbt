import bintray.Keys._
import sbt.Keys.resolvers
import sbt._

organization := "io.shedin.library"

name := "shedin-crud-lib"

version := "0.2.1"

lazy val shedinCrudLib = project.in(file("."))
  .settings(bintrayPublishSettings: _*)
  .settings(
    sbtPlugin := false,
    licenses += ("Apache-2.0", url("https://opensource.org/licenses/Apache-2.0")),
    publishMavenStyle := false,
    repository in bintray := "shedin",
    bintrayOrganization in bintray := None,
    resolvers += Resolver.url("edinhodzic", url("http://dl.bintray.com/edinhodzic/shedin"))(Resolver.ivyStylePatterns)
  )
  .settings(name := "shedin-crud-lib")
  .settings(scalaVersion := "2.12.2")
  .settings(libraryDependencies ++= Seq(
    "org.scala-lang" % "scala-library" % "2.12.2",
    "org.scalatest" % "scalatest_2.12" % "3.0.3" % Test,
    "org.mockito" % "mockito-all" % "1.10.19" % Test))
  .settings(scalacOptions ++= Seq("-deprecation", "-feature"))
  .settings(ivyScala := ivyScala.value map {
    _.copy(overrideScalaVersion = true)
  })
