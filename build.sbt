name := "KafkaExactlyOnce"

version := "1.0"

scalaVersion := "2.12.4"

libraryDependencies += "org.apache.kafka" % "kafka-clients" % "1.0.0"

mainClass in (Compile, run) := Some("Application")
