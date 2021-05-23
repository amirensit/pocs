package org.sid.demospringcloudstreamskafka.services;

import org.sid.demospringcloudstreamskafka.entities.PageEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

/**
 * This class is used as kafka consumer
 */
@Service
public class PageEventService {

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
}
