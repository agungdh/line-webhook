package com.agungdh.linechat.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeliveryContext {
    private boolean isRedelivery;
}
