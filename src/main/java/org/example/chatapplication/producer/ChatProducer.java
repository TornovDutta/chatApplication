package org.example.chatapplication.producer;
import org.example.chatapplication.model.Messages;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
@Service
public class ChatProducer {
    private final KafkaTemplate<String,Messages> kafkaTemplate;


    public ChatProducer(KafkaTemplate<String, Messages> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
     public void sendMessage(Messages msg){
        kafkaTemplate.send("chat-message",msg);

         System.out.println("sent message: " +msg);
     }

}
