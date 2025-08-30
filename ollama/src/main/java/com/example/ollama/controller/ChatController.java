package com.example.ollama.controller;

import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/ai")
public class ChatController {
    private final OllamaChatModel chat;

    public ChatController(OllamaChatModel chat) { this.chat = chat; }

    @GetMapping("/chat")
    public String chat(@RequestParam String msg) {
        return chat.call(msg); // "/ai/chat?msg=안녕" -> 모델 응답
    }
}
