package com.stackroute.chatservice.service;

import com.stackroute.chatservice.model.ChatMessage;
import com.stackroute.chatservice.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatService {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    public ChatMessage sendMessage(ChatMessage message) {
        message.setTimestamp(LocalDateTime.now());
        message.setRead(false);
        return chatMessageRepository.save(message);
    }

    public List<ChatMessage> getSessionMessages(String sessionId) {
        return chatMessageRepository.findBySessionIdOrderByTimestampAsc(sessionId);
    }

    public List<ChatMessage> getUnreadMessages(String sessionId) {
        return chatMessageRepository.findBySessionIdAndReadFalse(sessionId);
    }

    public ChatMessage markAsRead(String messageId) {
        ChatMessage message = chatMessageRepository.findById(messageId)
                .orElseThrow(() -> new RuntimeException("Message not found: " + messageId));
        message.setRead(true);
        return chatMessageRepository.save(message);
    }
}
