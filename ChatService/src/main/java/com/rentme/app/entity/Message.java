package com.rentme.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rentme.app.model.MessageStatus;
import com.rentme.app.model.MessageType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"conversation", "status", "replyToMessage", "attachment"})
@Entity
@Table(name = "_MESSAGE")
@DynamicUpdate
public class Message extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MESSAGE_ID_SEQUENCE_GENERATOR")
    @SequenceGenerator(name = "MESSAGE_ID_SEQUENCE_GENERATOR", sequenceName = "MESSAGE_ID_SEQUENCE_GENERATOR", allocationSize = 50, initialValue = 1)
    private Long id;

    private String content;

    @Enumerated(EnumType.STRING)
    private MessageType type;
    private String sender;
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "conversation_id")
    @JsonBackReference
    private Conversation conversation;

    @Enumerated(EnumType.STRING)
    private MessageStatus status = MessageStatus.SENT;

    private boolean isDeleted;

    @ManyToOne
    @JoinColumn(name="reply_to_message_id")
    private Message replyToMessage;

    @OneToOne(mappedBy = "message", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Attachment attachment;

}
