package com.stackroute.chatservice.controller;

import com.stackroute.chatservice.model.ChatMessage;
import com.stackroute.chatservice.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chat")
@CrossOrigin
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestBody ChatMessage message) {
        try {
            return new ResponseEntity<>(chatService.sendMessage(message), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/session/{sessionId}")
    public ResponseEntity<List<ChatMessage>> getSessionMessages(@PathVariable String sessionId) {
        return new ResponseEntity<>(chatService.getSessionMessages(sessionId), HttpStatus.OK);
    }

    @GetMapping("/session/{sessionId}/unread")
    public ResponseEntity<List<ChatMessage>> getUnreadMessages(@PathVariable String sessionId) {
        return new ResponseEntity<>(chatService.getUnreadMessages(sessionId), HttpStatus.OK);
    }

    @PutMapping("/read/{messageId}")
    public ResponseEntity<?> markAsRead(@PathVariable String messageId) {
        try {
            return new ResponseEntity<>(chatService.markAsRead(messageId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
