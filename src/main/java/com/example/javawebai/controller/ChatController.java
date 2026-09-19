package com.example.javawebai.controller;

import com.example.javawebai.dto.ChatRequest;
import com.example.javawebai.dto.ChatResponse;
import com.example.javawebai.service.ChatService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "http://localhost:4200")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public Mono<ChatResponse> chat(@RequestBody ChatRequest request) {
        return chatService.sendMessage(request.message());
    }
}
