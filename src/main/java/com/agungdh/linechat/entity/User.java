package com.agungdh.linechat.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;
    private String userId;
    private String displayName;
    private String pictureUrl;
    private String statusMessage;
    private String language;
    private Instant createdAt;
}
