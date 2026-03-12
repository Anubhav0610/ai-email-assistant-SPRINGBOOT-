package com.email.writer.service;

import com.email.writer.dto.EmailRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import reactor.core.publisher.Mono;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class EmailGeneratorService {

    private final WebClient webClient;

    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    public EmailGeneratorService(WebClient.Builder builder) {
        this.webClient = builder.build();
    }



    public Mono<String> generateEmailReply(EmailRequest emailRequest) {

        String prompt = buildPrompt(emailRequest);

        Map<String, Object> requestBody =
                Map.of(
                        "contents", java.util.List.of(
                                Map.of(
                                        "parts", java.util.List.of(
                                                Map.of("text", prompt)
                                        )
                                )
                        ),
                        "generationConfig", Map.of(
                                "temperature", 0.5,
                                "maxOutputTokens", 800
                        )
                );

        return webClient.post()
                .uri(geminiApiUrl + "?key=" + geminiApiKey)
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .accept(org.springframework.http.MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .map(this::extractResponseContent)
                .onErrorResume(e ->
                        Mono.just("Backend Error: " + e.getMessage()));
    }

    private String extractResponseContent(String response) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response);

            // Get parts array from Gemini response
            JsonNode parts = rootNode
                    .path("candidates")
                    .get(0)
                    .path("content")
                    .path("parts");

            // Combine all text parts
            StringBuilder result = new StringBuilder();

            for (JsonNode part : parts) {
                result.append(part.path("text").asText());
            }

            return result.toString();

        } catch (Exception e) {
            return "Error processing response: " + e.getMessage();
        }
    }

    private String buildPrompt(EmailRequest emailRequest) {

        StringBuilder prompt = new StringBuilder();

        prompt.append("You are an AI assistant that writes clear and professional email replies.\n");
        prompt.append("Write a complete email reply.\n");
        prompt.append("The reply should:\n");
        prompt.append("- Be logical and relevant to the email\n");
        prompt.append("- Contain proper sentences and structure\n");
        prompt.append("- Be polite and professional\n");
        prompt.append("- Be 4 to 6 sentences long\n");
        prompt.append("- Do NOT generate a subject line\n\n");

        if(emailRequest.getTone()!=null && !emailRequest.getTone().isEmpty()){
            prompt.append("Tone: ").append(emailRequest.getTone()).append("\n\n");
        }

        prompt.append("Original Email:\n");
        prompt.append(emailRequest.getEmailContent());
        prompt.append("\n\nReply:");

        return prompt.toString();
    }
}