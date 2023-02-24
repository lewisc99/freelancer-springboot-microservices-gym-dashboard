package com.lewis.msmessageemail.listener;

import com.lewis.msmessageemail.entities.MailEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.adapter.ConsumerRecordMetadata;
import org.springframework.stereotype.Component;

@Component
public class MailListener {

    @KafkaListener(topics = "user-message-topic", groupId = "group-1", containerFactory =
    "jsonKafkaListenerContainerFactory")
    public void mailEvent(MailEvent mailEvent, ConsumerRecordMetadata metadata)
    {
        System.out.println("Topic : " + metadata.topic() + " partition: ");
        System.out.println();
        System.out.println(mailEvent);

    }
}
