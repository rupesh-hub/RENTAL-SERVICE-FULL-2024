package com.rentme.app.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name="_PARTICIPANT")
@DynamicUpdate
public class Participant extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PARTICIPANT_ID_SEQUENCE_GENERATOR")
    @SequenceGenerator(name = "PARTICIPANT_ID_SEQUENCE_GENERATOR", sequenceName = "PARTICIPANT_ID_SEQUENCE_GENERATOR", allocationSize = 50, initialValue = 1)
    private Long id;

    private String username;
    private String role;
    private int unreadCount;
    private String profile;
    private boolean online;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastSeen;

    @ManyToOne
    @JoinColumn(name = "conversation_id")
    private Conversation conversation;

}
