package com.brightmeta

import java.util.concurrent.Executors
import java.util.{Collections, Properties}
import scala.collection.JavaConverters._

import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecord, KafkaConsumer}

/**
  * Created by John on 11/25/17.
  */
class ExactlyOnceConsumer {

  val topic = "test"
  val props = new Properties()
  props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
  props.put(ConsumerConfig.GROUP_ID_CONFIG, "1")
  props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer")
  props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer")

  val consumer = new KafkaConsumer[String, String](props)

  consumer.subscribe(Collections.singletonList(topic))

  while(true) {
    val records = consumer.poll(1000)

    for(record <- records.asScala) {
      System.out.println("Received message: (" + record.key() + ", " + record.value() + ") at offset " + record.offset())
    }
  }


}
