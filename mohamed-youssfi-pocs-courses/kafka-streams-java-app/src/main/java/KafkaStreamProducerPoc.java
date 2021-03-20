import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class KafkaStreamProducerPoc {
    String message;

    public static void main(String[] args) {
        new KafkaStreamProducerPoc().start();
    }

    private void start() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, "stream-producer-1"); // mandatory attribute
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);
        List<Character> characters = new ArrayList<>();
        for (char c = 'A'; c < 'Z'; c++) {
            characters.add(c);
        }
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(() -> {
            message = "";
            for (int i = 0; i < 10; i++) {
                message += " " + characters.get(new Random().nextInt(characters.size())); // space separated characters
            }
            kafkaProducer.send(new ProducerRecord<>("topic-for-streams", null, message),
                    (metadata, exception) -> System.out.println("sended message: " + message + "\\n with information: "
                            + metadata.topic()));
        }, 1000, 1000, TimeUnit.MILLISECONDS);
    }
}
