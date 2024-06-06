package com.rentme.app.entity;

import com.rentme.app.enumeration.NotificationType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_notifications")
@EntityListeners(AuditingEntityListener.class)
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "_notification_sequence_gen")
    @SequenceGenerator(name = "_notification_sequence_gen", sequenceName = "_notification_sequence_gen", initialValue = 1, allocationSize = 1)
    @Column(name="id", nullable = false, updatable = false, unique = true)
    private Long id;

    private NotificationType notificationType;
    private LocalDateTime notificationDate;
    private String orderConfirmation;
    private String paymentConfirmation;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(insertable = false, nullable = false)
    private LocalDateTime modifiedOn;
}
