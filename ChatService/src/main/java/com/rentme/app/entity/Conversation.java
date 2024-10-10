package com.rentme.app.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rentme.app.model.ConversationType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"participants", "messages"})
@Entity
@Table(name = "_CONVERSATION")
@DynamicUpdate
public class Conversation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONVERSATION_ID_SEQUENCE_GENERATOR")
    @SequenceGenerator(name = "CONVERSATION_ID_SEQUENCE_GENERATOR", sequenceName = "CONVERSATION_ID_SEQUENCE_GENERATOR", allocationSize = 50, initialValue = 1)
    private Long id;

    private String name;
    private String avatar;
    private String createdBy;

    @Enumerated(EnumType.STRING)
    private ConversationType type;

    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Participant> participants = new HashSet<>();

    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Message> messages = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "last_message_id")
    private Message lastMessage;

    public void addParticipant(Participant participant) {
        participants.add(participant);
        participant.setConversation(this);
    }

    public void removeParticipant(Participant participant) {
        participants.remove(participant);
        participant.setConversation(null);
    }

}

