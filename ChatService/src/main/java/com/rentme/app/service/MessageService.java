package com.rentme.app.service;

import com.rentme.app.entity.Conversation;
import com.rentme.app.entity.Message;
import com.rentme.app.model.MessageRequest;
import com.rentme.app.model.MessageResponse;
import com.rentme.app.model.MessageStatus;
import com.rentme.app.repository.ConversationRepository;
import com.rentme.app.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final ConversationRepository conversationRepository;

    public MessageResponse sendMessage(MessageRequest request) {
        Conversation conversation = conversationRepository.findById(request.getConversationId())
                .orElseThrow(() -> new RuntimeException("No conversation found for conversation " + request.getConversationId()));

        Message savedMessage = messageRepository.save(
                Message.builder()
                        .content(request.getContent())
                        .type(request.getType())
                        .sender(request.getSender())
                        .timestamp(LocalDateTime.now())
                        .isDeleted(false)
                        .conversation(conversation)
                        .status(MessageStatus.SENT)
                        .build()
        );

        conversation.setLastMessage(savedMessage);
        conversationRepository.save(conversation);

        return MessageResponse
                .builder()
                .id(savedMessage.getId())
                .status(savedMessage.getStatus())
                .sender(savedMessage.getSender())
                .content(savedMessage.getContent())
                .timestamp(savedMessage.getCreatedAt())
                .profile(
                        conversation
                                .getParticipants()
                                .stream()
                                .filter(
                                        participant -> participant.getUsername()
                                                .equals(request.getSender())
                                )
                                .findFirst()
                                .get()
                                .getProfile()
                )
                .build();

    }

    public Message updateMessageStatus(Long messageId, MessageStatus status) {

        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new RuntimeException("No message found for message " + messageId));

        //todo: unread count
        /** if (status == MessageStatus.READ) {
         Conversation conversation = message.getConversation();
         conversation.setUnreadCount(0);
         conversationRepository.save(conversation);
         } */

        message.setStatus(status);
        return messageRepository.save(message);
    }


}
