package org.amir.tutorial1;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class ProducerDemoKeys {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Logger  logger = LoggerFactory.getLogger(ProducerDemoKeys.class);

        // create producer property
        Properties properties = new Properties();
        String bootstrapServers = "172.21.95.164:9092";
        // properties.setProperty("bootstrap.servers", bootstrapServers); old way
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // create producer
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);

        for (int i = 0; i < 5; i++) {
            // create producerRecord
            String topic = "first_topic";
            String value = "hello world" + Integer.valueOf(i);
            String key = "id_" + Integer.valueOf(i);
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, key, value);

            logger.info("key: {}", key); // log the key

            // send data - asynchronous
            kafkaProducer.send(producerRecord, (recordMetadata, e) -> {
                // executes every time a record is successfully sent or an exception is thrown
                if (e == null) {
                    // the record was successfully sent
                    logger.info("Received new metadata:  \n" +
                                    "Topic: {} \n" +
                                    "key: {} \n" +
                                    "partitions: {} \n" +
                                    "offsets: {} \n" +
                                    "timestamps: {}",
                            recordMetadata.topic(),
                            producerRecord.key(),
                            recordMetadata.partition(),
                            recordMetadata.offset(),
                            recordMetadata.timestamp());
                } else {
                    logger.error("error while producing message: {}", e);
                }
            }).get();  // block the .send() to make it synchronous. Don't do this in production!
        }
        // flush data
        kafkaProducer.flush();

        // flush and close
        kafkaProducer.close();
    }
}
