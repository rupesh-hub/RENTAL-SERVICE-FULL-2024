package com.rentme.app.service;

import com.rentme.app.entity.Conversation;
import com.rentme.app.entity.Participant;
import com.rentme.app.entity.User;
import com.rentme.app.model.MessageResponse;
import com.rentme.app.model.ConversationRequest;
import com.rentme.app.model.ConversationResponse;
import com.rentme.app.model.ParticipantResponse;
import com.rentme.app.repository.ConversationRepository;
import com.rentme.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConversationService {

    private final ConversationRepository conversationRepository;
    private final UserRepository userRepository;

    public List<ConversationResponse> getConversations(String createdBy) {
        return Optional.ofNullable(conversationRepository.getConversations(createdBy))
                .orElse(Collections.emptyList())
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }


    public void createConversation(ConversationRequest request, String loggedInUser) {
        String username = Arrays.stream(request.getParticipants())
                .filter(participant -> !participant.equals(loggedInUser))
                .findFirst()
                .orElseThrow(() -> new RuntimeException());

        User user = userRepository.findByUsername(username)
                .orElseThrow();

        boolean isGroup = request.getType().name().equalsIgnoreCase("GROUP");

        String conversationName = isGroup ? request.getName() : user.getFirstName() + " " + user.getLastName();
        String avatar = isGroup ? request.getAvatar() : user.getProfile();

        // check conversation
        Conversation conversation = Conversation.builder()
                .name(conversationName)
                .avatar(avatar)
                .type(request.getType())
                .createdBy(loggedInUser)
                .participants(
                        Arrays.stream(request.getParticipants())
                                .map(participant -> {
                                            User chatUser = userRepository.findByUsername(participant)
                                                    .orElseThrow(() -> new RuntimeException("Could not find user by username."));
                                            return Participant
                                                    .builder()
                                                    .role("USER")
                                                    .unreadCount(0)
                                                    .username(chatUser.getUsername())
                                                    .lastSeen(new Date())
                                                    .profile(chatUser.getProfile())
                                                    .build();

                                        }
                                )
                                .collect(Collectors.toSet())
                )
                .build();
        conversationRepository.save(conversation);
    }

    public ConversationResponse getConversationById(Long conversationId) {
        return conversationRepository.findById(conversationId)
                .map(this::convert)
                .orElseThrow(() -> new RuntimeException("No conversation found for conversation id " + conversationId));
    }

    private ConversationResponse convert(Conversation conversation) {
        return ConversationResponse
                .builder()
                .id(conversation.getId())
                .name(conversation.getName())
                .avatar(conversation.getAvatar())
                .isGroup("GROUP".equals(conversation.getType().name()))
                .createdBy(conversation.getCreatedBy())
                .createdAt(conversation.getCreatedAt())
                .updatedAt(conversation.getUpdatedAt())
                .participants(
                        conversation.getParticipants()
                                .stream()
                                .map(participant -> ParticipantResponse
                                        .builder()
                                        .username(participant.getUsername())
                                        .profile(participant.getProfile())
                                        .lastSeen(participant.getLastSeen())
                                        .role(participant.getRole())
                                        .online(participant.isOnline())
                                        .build()
                                )
                                .collect(Collectors.toSet())
                )
                .messages(conversation.getMessages()
                        .stream()
                        .map(message -> MessageResponse.builder()
                                .id(message.getId())
                                .status(message.getStatus())
                                .content(message.getContent())
                                .timestamp(message.getCreatedAt())
                                .sender(message.getSender())
                                .profile(
                                        conversation
                                                .getParticipants()
                                                .stream()
                                                .filter(
                                                        participant -> participant.getUsername()
                                                                .equals(message.getSender())
                                                )
                                                .findFirst()
                                                .get()
                                                .getProfile()
                                )
                                .build())
                        .collect(Collectors.toList()))
                .lastMessage(
                        Optional.ofNullable(conversation.getLastMessage())
                                .map(message -> MessageResponse.builder()
                                        .id(message.getId())
                                        .status(message.getStatus())
                                        .content(message.getContent())
                                        .timestamp(message.getCreatedAt())
                                        .sender(message.getSender())
                                        .build()
                                )
                                .orElse(null)
                )
                .build();
    }

    public void addParticipant(String username, Long conversationId) {
        var conversation = conversationRepository.findById(conversationId)
                .orElseThrow(() -> new RuntimeException("No conversation found for conversation id " + conversationId));

        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("No user found for user " + username));

        conversation.addParticipant(Participant
                .builder()
                .username(user.getUsername())
                .profile(user.getProfile())
                .unreadCount(0)
                .role("USER")
                .online(false)
                .lastSeen(new Date())
                .build());

        conversationRepository.save(conversation);
    }
}
