package com.example.javawebai.service;

import com.example.javawebai.dto.ChatResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import tools.jackson.databind.JsonNode;

import java.util.List;
import java.util.Map;

@Service
public class ChatService {

    private final WebClient webClient;

    public ChatService(
            WebClient.Builder webClientBuilder,
            @Value("${openai.api.key}") String apiKey) {

        this.webClient = webClientBuilder
                .baseUrl("https://api.openai.com/v1")
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .build();
    }

    public Mono<ChatResponse> sendMessage(String message) {

        Map<String, Object> requestBody = Map.of(
                "model", "gpt-5-mini",
                "messages", List.of(
                        Map.of(
                                "role", "user",
                                "content", message
                        )
                )
        );

        return webClient
                .post()
                .uri("/chat/completions")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(response -> {
                    String reply = response
                            .path("choices")
                            .get(0)
                            .path("message")
                            .path("content")
                            .asText();

                    return new ChatResponse(reply);
                });
    }
}