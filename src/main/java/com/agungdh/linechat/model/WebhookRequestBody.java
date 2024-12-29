package com.agungdh.linechat.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class WebhookRequestBody {
    private String destination;
    private List<WebhookEvent> events;
}
