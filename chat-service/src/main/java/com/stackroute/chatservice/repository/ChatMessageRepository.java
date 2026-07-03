package com.stackroute.chatservice.repository;

import com.stackroute.chatservice.model.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
    List<ChatMessage> findBySessionIdOrderByTimestampAsc(String sessionId);
    List<ChatMessage> findBySenderEmailOrReceiverEmail(String senderEmail, String receiverEmail);
    List<ChatMessage> findBySessionIdAndReadFalse(String sessionId);
}
