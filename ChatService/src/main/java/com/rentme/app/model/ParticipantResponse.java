package com.rentme.app.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class ParticipantResponse {

    private String username;
    private String role;
    private int unreadCount;
    private String profile;
    private Date lastSeen;
    private boolean online;

}
