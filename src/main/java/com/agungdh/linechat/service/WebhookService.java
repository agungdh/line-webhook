package com.agungdh.linechat.service;

import com.agungdh.linechat.entity.Message;
import com.agungdh.linechat.model.WebhookRequestBody;
import com.agungdh.linechat.repository.MessageRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Slf4j
@RequiredArgsConstructor
public class WebhookService {
    private final MessageRepository messageRepository;

    public void process(String body) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        WebhookRequestBody webhookRequestBody;
        try {
            webhookRequestBody = objectMapper.readValue(body, WebhookRequestBody.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert JSON to User", e);
        }

        webhookRequestBody.getEvents().forEach(event -> {
            Message.MessageBuilder messageBuilder = Message
                    .builder()
                    .messageId(event.getMessage().getId())
                    .type(event.getMessage().getType())
                    .userId(event.getSource().getUserId())
                    .timestamp(event.getTimestamp())
                    .createdAt(Instant.now());

            if (event.getMessage().getType().equals("text")) {
                Message message = messageBuilder.message(event.getMessage().getText()).build();
                messageRepository.save(message);

                log.info("Processed: {}", message);
            }
        });
    }
}
