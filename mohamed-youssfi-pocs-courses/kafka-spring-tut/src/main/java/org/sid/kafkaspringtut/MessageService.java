package org.sid.kafkaspringtut;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @KafkaListener(topics = "quickstart-events", groupId = "ms-group")
    public void onMessage(ConsumerRecord<String, PageEvent> consumerRecord) {
        System.out.println("*******");
        System.out.println("key: " + consumerRecord.key());
        System.out.println("Page: " + consumerRecord.value().getPage());
        System.out.println("Date: " + consumerRecord.value().getDate());
        System.out.println("offset: " + consumerRecord.offset());
    }
}
