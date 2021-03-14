package org.sid.kafkaspringtut;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @KafkaListener(topics = "test1", groupId = "ms-group")
    public void onMessage(ConsumerRecord<String, String> consumerRecord) {
        System.out.println("*******");
        System.out.println("key: " + consumerRecord.key());
        System.out.println("value: " + consumerRecord.value());
        System.out.println("offset: " + consumerRecord.offset());
    }
}
