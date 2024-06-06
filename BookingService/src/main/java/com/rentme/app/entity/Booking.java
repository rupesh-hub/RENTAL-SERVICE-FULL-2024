package com.rentme.app.entity;

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
@Table(name = "_product_bookings")
@EntityListeners(AuditingEntityListener.class)
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "_product_booking_seq_generator")
    @SequenceGenerator(
            name = "_product_booking_seq_generator",
            sequenceName = "_product_booking_seq_generator",
            initialValue = 1,
            allocationSize = 1
    )
    @Column(name="id", nullable = false, updatable = false, unique = true)
    private Long id;

    private String userId;
    private String productId;
    private LocalDateTime bookingDate;
    private LocalDateTime returnDate;

}