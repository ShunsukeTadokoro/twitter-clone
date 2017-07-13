name := "twitter-clone"

organization := "io.github.todokr"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala, DockerPlugin)

scalaVersion := "2.11.8"

val dbConnector = "mysql" % "mysql-connector-java" % "5.1.42"

val akkaVersion = "2.4.4"

libraryDependencies ++= Seq(
  cache,
  ws,
  filters,
  evolutions,
  jdbc,
  dbConnector,
  "com.fasterxml.jackson.module" %% "jackson-module-scala"         % "2.7.2",
  "com.github.nscala-time"       %% "nscala-time"                  % "2.14.0",
  "org.scalikejdbc"              %% "scalikejdbc"                  % "2.5.1",
  "org.scalikejdbc"              %% "scalikejdbc-config"           % "2.5.1",
  "org.scalikejdbc"              %% "scalikejdbc-play-initializer" % "2.6.0",
  "com.lifeway"                  %% "play-modules-redis"           % "2.5.1",
  "org.webjars"                   % "swagger-ui"                   % "2.2.0",
  "org.scalatestplus.play"        % "scalatestplus-play_2.11"      % "3.1.0" % Test
)

// swaggerDomainNameSpaces := Seq("models")

import com.typesafe.sbt.packager.docker._

dockerBaseImage := "java:8-jre-alpine"

dockerCommands += Cmd("USER", "root")
dockerCommands += Cmd("RUN", "apk add --no-cache bash")
dockerCommands += Cmd("USER", "daemon")

dockerExposedPorts := Seq(9000, 9443)
