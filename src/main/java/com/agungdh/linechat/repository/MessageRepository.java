package com.agungdh.linechat.repository;

import com.agungdh.linechat.entity.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<Message, String> {
}
