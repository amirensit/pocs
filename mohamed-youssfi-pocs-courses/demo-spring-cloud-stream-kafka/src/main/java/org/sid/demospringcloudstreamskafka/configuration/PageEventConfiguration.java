package org.sid.demospringcloudstreamskafka.configuration;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.sid.demospringcloudstreamskafka.entities.PageEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * This class is used as kafka consumer
 */
@Service
public class PageEventConfiguration {

    /**
     * by default in spring cloud stream the name of topic is the name of this method.
     * We need to configure this if we want other name.
     * The default value is: pageEventConsumer-in-0.
     * We need to use this property in order to change topic name:
     * spring.cloud.steam.bindings.pageEventConsumer-in-0.destination:yourValue
     */
    @Bean // we need this annotation
    public Consumer<PageEvent> pageEventConsumer() {
        return (input) -> {
            System.out.println("*****");
            System.out.println(input.toString());
            System.out.println("*****");
        };
    }

    @Bean
    public Supplier<PageEvent> pageEventSupplier() {
        return () -> new PageEvent(Math.random() > 0.5 ? "P1" : "P2",
                Math.random() > 0.5 ? "u1" : "u2",
                LocalDate.now(),
                new Random().nextInt(9000));
    }

    @Bean
    public Function<PageEvent, PageEvent> pageEventFunction() {
        return (input) -> {
            input.setName("Page Event");
            input.setUser("UUU");
            return input;
        };
    }

    @Bean
    public Function<KStream<String, PageEvent>, KStream<String, Long>> kStreamFunction() {
        return (kstreamInput) -> {
            return kstreamInput
                    .filter((k, v) -> v.getDuration() > 100)
                    .map((k, v) -> new KeyValue<>(v.getName(), 0L))
                    .groupBy((k, v) -> k, Grouped.with(Serdes.String(), Serdes.Long()))
                    .windowedBy(TimeWindows.of(5000)) // just collect event that was produced during the last 5 seconds
                    .count()
                    .toStream()
                    .map((k, v) -> new KeyValue<>("->" + k.window().startTime() +
                            k.window().endTime() + ": " + k.key(), v));
        };
    }
}
