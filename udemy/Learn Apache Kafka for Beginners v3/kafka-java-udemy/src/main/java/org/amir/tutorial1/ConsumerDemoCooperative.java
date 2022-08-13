package org.amir.tutorial1;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class ConsumerDemoCooperative {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(ConsumerDemoCooperative.class);

        // create producer property
        Properties properties = new Properties();
        String bootstrapServers = "172.21.95.164:9092";
        String groupId = "my-third-application-group-id";
        var topic = "demo_java";

        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); // possible values to remember: none: do not start if there is no offsets / earliest: read from the beginning / latest: read only from now
        properties.setProperty(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG, CooperativeStickyAssignor.class.getName());
        // properties.setProperty(ConsumerConfig.GROUP_INSTANCE_ID_CONFIG, ""); // we need this when we want to use the static assignment of consumers to partitions

        // create consumer
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);

        // get a reference to the current thread
        final Thread mainThread = Thread.currentThread();

        // add the shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("Detecting a shutdown hook, let's exit by calling consumer.wakeup()...");
            kafkaConsumer.wakeup();

            // join the main thread to allow the execution of the code in the main thread
            try {
                mainThread.join();
            } catch (InterruptedException e) {

            }
        }));

        try {

            // subscribe to topic
            kafkaConsumer.subscribe(Arrays.asList(topic));

            // poll for new data
            while (true) {
                logger.info("polling ");
                ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                    logger.info("key: {} \n" +
                                    "value: {} \n " +
                                    "partition: {} \n" +
                                    "offset: {} \n",
                            consumerRecord.key(),
                            consumerRecord.value(),
                            consumerRecord.partition(),
                            consumerRecord.offset());
                }
            }
        } catch (WakeupException e) {
            logger.info("wakeupException !");
            // we ignore this exception as it is an expected exception when closing a consumer
        } catch (Exception e) {
            logger.error("unexpected exception !");
        } finally {
            kafkaConsumer.close(); // this will also commit the offset
            logger.info("the consumer is gracefully closed");
        }


    }
}
