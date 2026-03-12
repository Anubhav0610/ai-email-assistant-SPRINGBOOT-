package com.email.writer.controller;

import com.email.writer.dto.EmailRequest;
import com.email.writer.service.EmailGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
@CrossOrigin(origins="*")
public class EmailGeneratorController {

    private final EmailGeneratorService emailGeneratorService;

    @PostMapping("/generate")
    public Mono<ResponseEntity<String>> generateEmail(
            @RequestBody EmailRequest emailRequest) {

        return emailGeneratorService.generateEmailReply(emailRequest)
                .map(response -> ResponseEntity.ok(response));
    }
}