name := "wj13-cassandra-app-stub-play"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache
)     

libraryDependencies += "com.datastax.cassandra" % "cassandra-driver-core" % "1.0.3"

play.Project.playJavaSettings
