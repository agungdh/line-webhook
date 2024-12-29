package com.agungdh.linechat.controller;

import com.agungdh.linechat.helper.LineSignatureValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/webhook")
@RequiredArgsConstructor
public class WebhookController {
    private final LineSignatureValidator signatureValidator;

    @Value("${line.channel.secret.agungdh}")
    private String channelSecretAgungDH;
    @Value("${line.channel.secret.akun2}")
    private String channelSecretAkun2;

    @PostMapping("/agungdh")
    public ResponseEntity<String> agungdh(@RequestBody String body, @RequestHeader("X-Line-Signature") String xLineSignature) {
        // Validate signature
        if (!signatureValidator.validateSignature(channelSecretAgungDH, xLineSignature, body)) {
            log.error("Invalid signature: {} data: {}", xLineSignature, body);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid signature");
        }

        log.info("Message received: {}", body);
        return ResponseEntity.ok("OK");
    }

    @PostMapping("/akun2")
    public ResponseEntity<String> akun2(@RequestBody String body, @RequestHeader("X-Line-Signature") String xLineSignature) {
        // Validate signature
        if (!signatureValidator.validateSignature(channelSecretAkun2, xLineSignature, body)) {
            log.error("Invalid signature: {} data: {}", xLineSignature, body);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid signature");
        }

        log.info("Message received: {}", body);
        return ResponseEntity.ok("OK");
    }
}
