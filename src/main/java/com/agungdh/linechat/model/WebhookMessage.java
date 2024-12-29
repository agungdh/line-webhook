package com.agungdh.linechat.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WebhookMessage {
    private String id;
    private String type;
    private String text;
    private String timestamp;
}
