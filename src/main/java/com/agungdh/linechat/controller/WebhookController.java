package com.agungdh.linechat.controller;

import com.agungdh.linechat.helper.LineSignatureValidator;
import com.agungdh.linechat.model.WebhookRequestBody;
import com.agungdh.linechat.service.WebhookService;
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
    private final WebhookService webhookService;

    @Value("${line.channel.secret.agungdh}")
    private String channelSecretAgungDH;
    @Value("${line.channel.secret.akun2}")
    private String channelSecretAkun2;

    private enum Channel {
        AGUNGDH, AKUN2;
    }

    @PostMapping("/agungdh")
    public ResponseEntity<Void> agungdh(@RequestBody String body, @RequestHeader("X-Line-Signature") String xLineSignature) {
        return webhook(body, xLineSignature, Channel.AGUNGDH);
    }

    @PostMapping("/akun2")
    public ResponseEntity<Void> akun2(@RequestBody String body, @RequestHeader("X-Line-Signature") String xLineSignature) {
        return webhook(body, xLineSignature, Channel.AKUN2);
    }

    private ResponseEntity<Void> webhook(@RequestBody String body, @RequestHeader("X-Line-Signature") String xLineSignature, Channel channel) {
        String channelSecret = switch (channel) {
            case AGUNGDH -> channelSecretAgungDH;
            case AKUN2 -> channelSecretAkun2;
        };

        // Validate signature
        if (!signatureValidator.validateSignature(channelSecret, xLineSignature, body)) {
            log.error("Invalid signature: {} data: {}", xLineSignature, body);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        log.info("Message received: {} data: {}", xLineSignature, body);

        webhookService.process(body);

        return ResponseEntity.ok().build();
    }
}
