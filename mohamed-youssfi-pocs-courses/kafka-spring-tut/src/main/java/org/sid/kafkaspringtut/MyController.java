package org.sid.kafkaspringtut;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Random;

@RestController
public class MyController {

    private final KafkaTemplate<String, PageEvent> kafkaTemplate;

    public MyController(KafkaTemplate<String, PageEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /*@PostMapping("/send/{topic}/{message}")
    public String send(@PathVariable String topic, @PathVariable String message) {
        kafkaTemplate.send(topic, String.valueOf(message.length()), message);
        return "sending message ...";
    }*/

    @PostMapping("/sendObject/{topic}/{page}")
    public String sendObject(@PathVariable String topic, @PathVariable String page) {
        PageEvent pageEvent = new PageEvent();
        pageEvent.setPage(page);
        pageEvent.setDate(LocalDate.now());
        pageEvent.setDuration(new Random().nextInt(1000));
        kafkaTemplate.send(topic, String.valueOf(pageEvent.getPage()), pageEvent);
        return "sending message ...";
    }
}
