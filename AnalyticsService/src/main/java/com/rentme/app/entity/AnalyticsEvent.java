package com.rentme.app.entity;

import com.rentme.app.enumeration.EventType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "_product_cart")
@EntityListeners(AuditingEntityListener.class)
@NamedQueries({
        @NamedQuery(name = "Cart.findByProductIdAndUserId",
                query = "SELECT c FROM AnalyticsEvent c WHERE c.productId = :productId AND c.userId = :userId"),
        @NamedQuery(name = "Cart.findByUserId",
                query = "SELECT c FROM AnalyticsEvent c WHERE c.userId = :userId")
})
public class AnalyticsEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "_product_cart_seq_generator")
    @SequenceGenerator(
            name = "_product_cart_seq_generator",
            sequenceName = "_product_cart_seq_generator",
            initialValue = 1,
            allocationSize = 1
    )
    @Column(name="id", nullable = false, updatable = false, unique = true)
    private Long id;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    private String userId;
    private String productId;
    private LocalDateTime timestamp;
    private String details;

}
