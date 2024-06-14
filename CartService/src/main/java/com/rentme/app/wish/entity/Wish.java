package com.rentme.app.wish.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "_product_wish")
@EntityListeners(AuditingEntityListener.class)
@NamedQueries({
        @NamedQuery(name = "Wish.findByProductIdAndUserId",
                query = "SELECT w FROM Wish w WHERE w.productId = :productId AND w.userId = :userId"),
        @NamedQuery(name = "Wish.findByUserId",
                query = "SELECT w FROM Wish w WHERE w.userId = :userId")
})
public class Wish {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "_product_wish_seq_generator")
    @SequenceGenerator(
            name = "_product_wish_seq_generator",
            sequenceName = "_product_wish_seq_generator",
            initialValue = 1,
            allocationSize = 1
    )
    @Column(name="id", nullable = false, updatable = false, unique = true)
    private Long id;

    private String productId;
    private String username;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime modifiedOn;

}
