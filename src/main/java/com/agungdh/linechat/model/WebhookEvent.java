package com.agungdh.linechat.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WebhookEvent {
    private String type;
    private String mode;
    private Long timestamp;
    private WebhookEventSource source;
    private String webhookEventId;
    private WebhookDeliveryContext deliveryContext;
    private WebhookMessage message;
}
