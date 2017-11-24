package com.brightmeta
import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
/**
  * Created by John on 11/24/17.
  */
class ExactlyOnceProducer {

  val props = new Properties()

  props.put("bootstrap.servers", "localhost:9092")
  props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

  val producer = new KafkaProducer[String, String](props)
  val topic = "test"

  for(i <- 1 to 50) {
    val record = new ProducerRecord(topic, "key", s"Hello $i")
    producer.send(record)
  }

  producer.close()
}
