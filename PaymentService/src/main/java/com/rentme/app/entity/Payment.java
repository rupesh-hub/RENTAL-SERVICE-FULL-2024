package com.rentme.app.entity;

import com.rentme.app.enumeration.PaymentMethod;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_payments")
@EntityListeners(AuditingEntityListener.class)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "_payment_id_sequence_gen")
    @SequenceGenerator(name = "_payment_id_sequence_gen", sequenceName = "_payment_id_sequence_gen", initialValue = 1, allocationSize = 1)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private Long id;

    private String user;
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    private int quantity;
    private String product;

    //billing address
    //credit card information
    //coupon code

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(insertable = false, nullable = false)
    private LocalDateTime modifiedOn;

}
