package com.rentme.app.entity;

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
@Table(name = "_order_lines")
@EntityListeners(AuditingEntityListener.class)
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "_order_line_sequence_gen")
    @SequenceGenerator(name = "_order_line_sequence_gen", sequenceName = "_order_line_sequence_gen", initialValue = 1, allocationSize = 1)
    @Column(name="id", nullable = false, updatable = false, unique = true)
    private Long id;

    private String productId;
    private double quantity;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(insertable = false, nullable = false)
    private LocalDateTime modifiedOn;

    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;

}
