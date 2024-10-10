package com.rentme.app.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConversationResponse {

    private Long id;
    private String name;
    private String avatar;
    private String createdBy;
    private ConversationType type;
    private Date createdAt;
    private Date updatedAt;
    private boolean isGroup;

    private Set<ParticipantResponse> participants;
    private List<MessageResponse> messages;

    private MessageResponse lastMessage;

}
