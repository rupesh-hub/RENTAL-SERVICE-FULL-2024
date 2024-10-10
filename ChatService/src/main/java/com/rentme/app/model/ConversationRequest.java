package com.rentme.app.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConversationRequest {

    private String name;
    private String avatar;
    private String createdBy;

    @Enumerated(EnumType.STRING)
    private ConversationType type;

    String[] participants;

}
