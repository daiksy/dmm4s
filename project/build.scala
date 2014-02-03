import com.typesafe.sbt.SbtScalariform.ScalariformKeys
import sbt._
import sbt.Keys._
import scalariform.formatter.preferences._
import com.typesafe.sbt.SbtScalariform

object dmm4sBuild extends Build {
  val appName = "dmm4s"
  val appOrganization = "com.github.daiksy"
  val appVersion  = "0.1-SNAPSHOT"
  val appScalaVersion = "2.10.3"

  val main = Project(
    appName,
    base = file("."),
    settings = Defaults.defaultSettings ++ Seq(
      organization := appOrganization,
      version := appVersion,
      scalaVersion := appScalaVersion,
      libraryDependencies ++= Seq(
        "org.apache.httpcomponents" % "httpclient" % "4.2.5",
        "org.slf4j" % "slf4j-api" % "1.7.2",
        "net.liftweb" % "lift-json_2.8.0" % "2.3"
      ) ++ testDependencies,
      resolvers ++= Seq(
        "snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
        "releases" at "http://oss.sonatype.org/content/repositories/releases",
        "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
      )
    ) ++  formatSettings ++ coverallsSetting
  ).settings(SbtScalariform.scalariformSettings: _*)

  lazy val testDependencies = Seq(
	  "junit" % "junit" % "4.7" % "test",
	  "org.scalaz" %% "scalaz-core" % "7.0.0" % "test",
	  "org.specs2" %% "specs2" % "1.13" % "test",
    "org.mockito" % "mockito-all" % "1.9.5" % "test"
  )

  lazy val formatSettings = Seq(
    ScalariformKeys.preferences := FormattingPreferences()
    .setPreference(IndentWithTabs, true)
    .setPreference(DoubleIndentClassDeclaration, true)
    .setPreference(PreserveDanglingCloseParenthesis, true)
  )

  lazy val coverallsSetting = Seq(
    CoverallsPlugin.singleProject: _*
  )
}
