import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.*;

import java.time.Duration;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Properties;

public class KafkaStreamConsumerPoc {

    public static void main(String[] args) {
        new KafkaStreamConsumerPoc().start();
    }

    private void start() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "streams-consumer-1"); // mandatory in consumer config as I know
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        properties.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 1000); // unable to understand what does this property do
        StreamsBuilder streamsBuilder = new StreamsBuilder();
        KStream<String, String> kStream = streamsBuilder.stream("topic-for-streams", Consumed.with(Serdes.String(), Serdes.String()));
        /* kStream.foreach((k, v) -> {
            System.out.println("key: " + k);
            System.out.println("value: " + v);
        });*/
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.systemDefault());
        KTable<Windowed<String>, Long> resultKTable = kStream.flatMapValues(value -> Arrays.asList(value.split("\\W+")))
                .map((k, v) -> new KeyValue<>(k, v.toLowerCase()))
                .filter((k, v) -> v.equals("a") || v.equals("b"))
                .groupBy((k, v) -> v)
                .windowedBy(TimeWindows.of(Duration.ofSeconds(5))) // statistics of only the last 5 seconds
                .count(Materialized.as("count-analytics")); // give a name to that kTable to be used next

        resultKTable.toStream()
                .map((k, v) -> new KeyValue<>(formatter.format(k.window().startTime()) + "-" + formatter.format(k.window().endTime()) + "-" + k.key(), v))
                .to("result-topic", Produced.with(Serdes.String(), Serdes.Long()));


        // start kafka streams
        Topology topology = streamsBuilder.build();
        KafkaStreams kafkaStreams = new KafkaStreams(topology, properties);
        kafkaStreams.start();
    }
}
