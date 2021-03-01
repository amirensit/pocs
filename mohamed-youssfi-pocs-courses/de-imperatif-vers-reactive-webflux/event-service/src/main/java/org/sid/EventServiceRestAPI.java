package org.sid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.Stream;

@RestController
public class EventServiceRestAPI {

    @GetMapping(value = "/streamEvents/{id}", produces = MediaType.APPLICATION_NDJSON_VALUE) // APPLICATION_STREAM_JSON_VALUE is deprecated.
    public Flux<Event> listEvents(@PathVariable String id) {
        Flux<Long> interval = Flux.interval(Duration.ofMillis(1000)); // every second
        Flux<Event> events = Flux.fromStream(Stream.generate(() -> {
            Event event = new Event();
            event.setInstant(Instant.now());
            event.setSocieteId(id);
            event.setValue(100 + Math.random() * 1000);
            return event;
        }));
        return Flux.zip(interval, events)
                .map(data -> data.getT2());
    }

    static class Event {
        private Instant instant;
        private double value;
        private String societeId;

        public Event() {
        }

        public Instant getInstant() {
            return instant;
        }

        public void setInstant(Instant instant) {
            this.instant = instant;
        }

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }

        public String getSocieteId() {
            return societeId;
        }

        public void setSocieteId(String societeId) {
            this.societeId = societeId;
        }
    }
}
