import sbt._
import de.element34.sbteclipsify._

class HelloWorldProject(info: ProjectInfo) extends DefaultProject(info) with Eclipsify with IdeaProject {
  override def libraryDependencies = Set(
    "org.scalatest" % "scalatest" % "1.2" % "test->default" withSources (),
    "log4j" % "log4j" % "1.2.16" withSources () withJavadoc (),
    "junit" % "junit" % "4.8.2" % "test->default" withSources() ,
    "org.scala-lang" % "scala-swing" % "2.8.1" withSources ()) ++ super.libraryDependencies

  override def mainClass = Some("sim.Main")

}
