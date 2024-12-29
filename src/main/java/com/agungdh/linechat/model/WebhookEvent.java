package com.agungdh.linechat.model;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class WebhookEvent {
    private String type;
    private String mode;
    private Instant timestamp;
    private WebhookEventSource source;
    private String webhookEventId;
//    private DeliveryContext deliveryContext;
}
