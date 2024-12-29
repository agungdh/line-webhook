package com.agungdh.linechat.service;

import com.agungdh.linechat.model.WebhookRequestBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WebhookService {
    public void process(WebhookRequestBody requestBody) {
        log.info("Processed: {}", requestBody);
        System.out.println(requestBody.getEvents().getFirst().getType());
    }
}
