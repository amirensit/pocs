package org.amir.tutorial1;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class ProducerDemo {
    public static void main(String[] args) {
        // create producer property
        Properties properties = new Properties();
        String bootstrapServers = "172.20.193.88:9092";
        // properties.setProperty("bootstrap.servers", bootstrapServers); old way
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // create producer
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);

        // create producerRecord
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>("first_topic", "hello world");

        // send data - asynchronous
        kafkaProducer.send(producerRecord);

        // flush data
        kafkaProducer.flush();

        // flush and close
        kafkaProducer.close();
    }
}
