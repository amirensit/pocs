import java.util.Properties;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class ProducerApp {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, "client-producer-1"); // mandatory attribute
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer(properties);
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(() -> {
            System.out.println("----------");
            String key = String.valueOf(new Random().nextInt(10));
            String value = String.valueOf(new Random().nextDouble() * 0.9);
            kafkaProducer.send(new ProducerRecord<> ("test1", key, value), (metadata, ex) -> {
                System.out.println("message sent");
                System.out.println("metadata.partition: " + metadata.partition());
                System.out.println("metadata.offset: " + metadata.offset());
            });
        }, 1000, 1000, TimeUnit.MILLISECONDS);
    }
}