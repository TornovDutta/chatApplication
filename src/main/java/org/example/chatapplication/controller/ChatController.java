package org.example.chatapplication.controller;

import org.example.chatapplication.consumer.ChatConsumer;
import org.example.chatapplication.model.Messages;
import org.example.chatapplication.producer.ChatProducer;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/message")
public class ChatController {
    private final ChatProducer producer;
    private final ChatConsumer consumer;

    public ChatController(ChatProducer producer, ChatConsumer consumer) {
        this.producer = producer;
        this.consumer = consumer;
    }

    @GetMapping("")
    public List<String> chat(){
        return consumer.getMessages();
    }
    @PostMapping()
    public void sendMessage(@RequestParam Messages msg){
        producer.sendMessage(msg);
    }

}
