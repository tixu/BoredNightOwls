import sbt._
import de.element34.sbteclipsify._
import java.util.Date


class TutorialOneProject(info: ProjectInfo) extends DefaultProject(info) with AkkaProject with Eclipsify{


	 val akkaSTM    = akkaModule("stm")
     val akkaRemote = akkaModule("remote")
     val akkaHTTP = akkaModule("http")
     
     

    val Glassfish = "JBoss Repo" at "http://repository.jboss.org/nexus/content/groups/public/"
    val jsr250           = Dependencies.jsr250
    val javax_servlet30  = Dependencies.javax_servlet_30
    val jetty            = Dependencies.jetty
    val jersey           = Dependencies.jersey_server
    val jsr311           = Dependencies.jsr311
//   val commons_codec    = Dependencies.commons_codec

  object Repositories {
    lazy val EmbeddedRepo           = MavenRepository("Embedded Repo", (info.projectPath / "embedded-repo").asURL.toString)
    lazy val AkkaRepo               = MavenRepository("Akka Repository", "http://akka.io/repository")
    lazy val CodehausRepo           = MavenRepository("Codehaus Repo", "http://repository.codehaus.org")
    lazy val GuiceyFruitRepo        = MavenRepository("GuiceyFruit Repo", "http://guiceyfruit.googlecode.com/svn/repo/releases/")
    lazy val JBossRepo              = MavenRepository("JBoss Repo", "http://repository.jboss.org/nexus/content/groups/public/")
    lazy val JavaNetRepo            = MavenRepository("java.net Repo", "http://download.java.net/maven/2")
    lazy val SonatypeSnapshotRepo   = MavenRepository("Sonatype OSS Repo", "http://oss.sonatype.org/content/repositories/releases")
    lazy val GlassfishRepo          = MavenRepository("Glassfish Repo", "http://download.java.net/maven/glassfish")
    lazy val ScalaToolsRelRepo      = MavenRepository("Scala Tools Releases Repo", "http://scala-tools.org/repo-releases")
    lazy val DatabinderRepo         = MavenRepository("Databinder Repo", "http://databinder.net/repo")
    lazy val ScalaToolsSnapshotRepo = MavenRepository("Scala-Tools Snapshot Repo", "http://scala-tools.org/repo-snapshots")
    lazy val SunJDMKRepo            = MavenRepository("WP5 Repository", "http://wp5.e-taxonomy.eu/cdmlib/mavenrepo")
  }

 // -------------------------------------------------------------------------------------------------------------------
  // Versions
  // -------------------------------------------------------------------------------------------------------------------

  lazy val JACKSON_VERSION       = "1.8.0"
  lazy val JERSEY_VERSION        = "1.3"
  lazy val MULTIVERSE_VERSION    = "0.6.2"
  lazy val SCALATEST_VERSION     = "1.4.1"
  lazy val JETTY_VERSION         = "7.4.0.v20110414"
  lazy val JAVAX_SERVLET_VERSION = "3.0"
  lazy val SLF4J_VERSION         = "1.6.0"
  lazy val ZOOKEEPER_VERSION     = "3.4.0"




object Dependencies {
	     lazy val jsr250           = "javax.annotation"            % "jsr250-api"              % "1.0" % "compile" //CDDL v1
         lazy val javax_servlet_30 = "org.glassfish"               % "javax.servlet"           % JAVAX_SERVLET_VERSION % "provided" //CDDL v1
     	 lazy val jetty            = "org.eclipse.jetty"           % "jetty-server"            % JETTY_VERSION % "provided" //Eclipse license
	     lazy val jersey_server    = "com.sun.jersey"              % "jersey-server"           % JERSEY_VERSION % "provided" //CDDL v1
         lazy val jsr311           = "javax.ws.rs"                 % "jsr311-api"              % "1.1" % "compile" //CDDL v1
         lazy val commons_codec    = "commons-codec"               % "commons-codec"           % "1.4" % "compile" //ApacheV2
      

}

}
