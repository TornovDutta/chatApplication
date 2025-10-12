package org.example.chatapplication.consumer;

import org.example.chatapplication.model.Messages;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatConsumer {

    @KafkaListener(topics = "chat-message",groupId = "chat-group")
    public void  listen(String msg) {
        System.out.println("Received message:"+msg);
    }
}
