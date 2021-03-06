name := "wj13-cassandra"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache
)     

libraryDependencies += "com.datastax.cassandra" % "cassandra-driver-core" % "2.1.1"

play.Project.playJavaSettings
