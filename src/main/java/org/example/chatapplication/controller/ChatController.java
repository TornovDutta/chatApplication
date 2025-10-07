package org.example.chatapplication.controller;

import org.example.chatapplication.producer.ChatProducer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChatController {
    private final ChatProducer producer;

    public ChatController(ChatProducer producer) {
        this.producer = producer;
    }
    @GetMapping("chat")
    public String chat(){
        return null;
    }

}
