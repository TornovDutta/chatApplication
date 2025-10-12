package org.example.chatapplication.consumer;

import org.example.chatapplication.model.Messages;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatConsumer {
    private final List<String> messageStore=new ArrayList<>();

    @KafkaListener(topics = "chat-message",groupId = "chat-group")
    public void  listen(String msg) {
        messageStore.add(msg);
        System.out.println("Received message:"+msg);
    }
    public List<String> getMessages(){
        return new ArrayList<>(messageStore);
    }
}
