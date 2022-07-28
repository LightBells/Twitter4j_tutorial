ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file(".")).settings(
  name := "twitter4j_tutorial",
  assemblyJarName := s"${name.value}.jar",
  libraryDependencies += "org.twitter4j" % "twitter4j" % "4.0.0",
  )

