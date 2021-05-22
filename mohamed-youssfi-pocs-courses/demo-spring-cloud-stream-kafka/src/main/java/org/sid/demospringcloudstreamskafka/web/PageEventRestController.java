package org.sid.demospringcloudstreamskafka.web;

import org.sid.demospringcloudstreamskafka.entities.PageEvent;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Random;

@RestController
public class PageEventRestController {

    private final StreamBridge streamBridge;

    public PageEventRestController(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }


    @GetMapping("/publish/{topic}/{name}")
    public void publish(@PathVariable String topic, @PathVariable String name) {
        PageEvent pageEvent = new PageEvent(name, Math.random() > 0.5 ? "u1" : "u2",
                LocalDate.now(), new Random().nextInt(9000)); // random value between 0 and 9000
        streamBridge.send(topic, pageEvent);
    }
}
