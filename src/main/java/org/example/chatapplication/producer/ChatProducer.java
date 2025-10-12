package org.example.chatapplication.producer;


import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatProducer {
    private final KafkaTemplate<String,String> kafkaTemplate;


    public ChatProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
     public void sendMessage(String msg){
        kafkaTemplate.send("chat-message",msg);

         System.out.println("sent message: " +msg);
     }

}
