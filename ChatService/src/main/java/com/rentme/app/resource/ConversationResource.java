package com.rentme.app.resource;

import com.rentme.app.entity.Message;
import com.rentme.app.entity.Notification;
import com.rentme.app.model.*;
import com.rentme.app.service.ConversationService;
import com.rentme.app.service.MessageService;
import com.rentme.app.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conversations")
@RequiredArgsConstructor
public class ConversationResource {

    private final ConversationService conversationService;
    private final MessageService messageService;
    private final NotificationService notificationService;

    //fetch chats
    @GetMapping("/conversation.all")
    public ResponseEntity<List<ConversationResponse>> allConversations(@RequestParam("creator") String createdBy) {
        return ResponseEntity.ok(conversationService.getConversations(createdBy));
    }

    @GetMapping("/conversation.byId")
    public ResponseEntity<ConversationResponse> allConversationsById(@RequestParam("conversationId") Long conversationId) {
        return ResponseEntity.ok(conversationService.getConversationById(conversationId));
    }

    //add chat
    @PostMapping("/conversation.create")
    public ResponseEntity<Void> saveConversation(@RequestBody ConversationRequest request) {
        conversationService.createConversation(request, "rupesh");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/participant.add")
    public ResponseEntity<Void> addParticipant(@RequestParam("username") String username,
                                               @RequestParam("conversationId") Long conversationId) {
        conversationService.addParticipant(username, conversationId);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{conversationId}/status")
    public ResponseEntity<Message> updateStatus(
            @PathVariable("conversationId") Long conversationId,
            @RequestParam MessageStatus status
    ) {
        return ResponseEntity.ok(messageService.updateMessageStatus(conversationId, status));
    }


    //create conversation
    @PostMapping("/message.send")
    public ResponseEntity<MessageResponse> sendMessage(@RequestBody MessageRequest request) {
        return new ResponseEntity<>(messageService.sendMessage(request), HttpStatus.CREATED);
    }

    @PostMapping("/notification.save")
    public ResponseEntity<Void> saveNotification(@RequestBody NotificationRequest request) {
        notificationService.save(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/notification.all")
    public ResponseEntity<List<NotificationResponse>> getAllNotifications(@RequestParam("username") String username) {
        return ResponseEntity.ok(notificationService.getNotifications(username));
    }

}
