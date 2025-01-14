package com.rentme.app.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "_ATTACHMENT")
@DynamicUpdate
public class Attachment {

    enum Type{
        IMAGE,
        VIDEO,
        AUDIO,
        DOCUMENT
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ATTACHMENT_ID_SEQUENCE_GENERATOR")
    @SequenceGenerator(name = "ATTACHMENT_ID_SEQUENCE_GENERATOR", sequenceName = "ATTACHMENT_ID_SEQUENCE_GENERATOR", allocationSize = 50, initialValue = 1)
    private Long id;
    private String fileName;
    private String url;

    @Enumerated(EnumType.STRING)
    private Type type; // image, video, audio, document, etc.

    @OneToOne
    @JoinColumn(name = "message_id")
    private Message message;

}
