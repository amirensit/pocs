package org.sid.demospringcloudstreamskafka.web;

import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Windowed;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyWindowStore;
import org.sid.demospringcloudstreamskafka.entities.PageEvent;
import org.springframework.cloud.stream.binder.kafka.streams.InteractiveQueryService;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * This class is used as kafka producer
 */
@RestController
public class PageEventRestController {

    private final StreamBridge streamBridge;

    private final InteractiveQueryService interactiveQueryService;

    public PageEventRestController(StreamBridge streamBridge, InteractiveQueryService interactiveQueryService) {
        this.streamBridge = streamBridge;
        this.interactiveQueryService = interactiveQueryService;
    }


    @GetMapping("/publish/{topic}/{name}")
    public void publish(@PathVariable String topic, @PathVariable String name) {
        PageEvent pageEvent = new PageEvent(name, Math.random() > 0.5 ? "u1" : "u2",
                LocalDate.now(), new Random().nextInt(9000)); // random value between 0 and 9000
        streamBridge.send(topic, pageEvent);
    }

    @GetMapping(path = "/analytics",produces = MediaType.TEXT_EVENT_STREAM_VALUE) // server-sent events
    public Flux<Map<String, Long>> analytics(){
        return Flux.interval(Duration.ofSeconds(1))
                .map(sequence->{
                    Map<String,Long> stringLongMap=new HashMap<>();
                    ReadOnlyWindowStore<String, Long> windowStore = interactiveQueryService.getQueryableStore("page-count", QueryableStoreTypes.windowStore());
                    Instant now=Instant.now();
                    Instant from=now.minusMillis(5000);
                    KeyValueIterator<Windowed<String>, Long> fetchAll = windowStore.fetchAll(from, now);
                    //WindowStoreIterator<Long> fetchAll = windowStore.fetch(page, from, now);
                    while (fetchAll.hasNext()){
                        KeyValue<Windowed<String>, Long> next = fetchAll.next();
                        stringLongMap.put(next.key.key(),next.value);
                    }
                    return stringLongMap;
                }).share();
    }
}
