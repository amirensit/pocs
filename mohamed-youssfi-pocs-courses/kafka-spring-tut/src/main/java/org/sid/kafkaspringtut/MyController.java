package org.sid.kafkaspringtut;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    private final KafkaTemplate<String, String> kafkaTemplate;


    public MyController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/send/{topic}/{message}")
    public String send(@PathVariable String topic, @PathVariable String message) {
        kafkaTemplate.send(topic, String.valueOf(message.length()), message);
        return "sending message ...";
    }
}
