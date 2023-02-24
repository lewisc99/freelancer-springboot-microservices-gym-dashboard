package com.lewis.msmessageemail.listener;

import com.lewis.msmessageemail.entities.MailEvent;
import com.lewis.msmessageemail.entities.MailModel;
import com.lewis.msmessageemail.service.contract.SendingEmailService;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.adapter.ConsumerRecordMetadata;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.HashMap;

@Component
public class MailListener {

    @Autowired
    private SendingEmailService    sendingEmailService;

    @KafkaListener(topics = "user-message-topic", groupId = "group-1", containerFactory =
    "jsonKafkaListenerContainerFactory")
    public void mailEvent(MailEvent mailEvent, ConsumerRecordMetadata metadata) throws MessagingException, TemplateException, IOException {
        System.out.println(mailEvent);
        MailModel  mailModel = new MailModel("luizdoidosonavide@gmail.com", mailEvent.getEmail(), mailEvent.getEmail(), mailEvent.getSubject(), mailEvent.getText(), new HashMap<>());
        sendingEmailService.sendEmail(mailModel);
    }
}
