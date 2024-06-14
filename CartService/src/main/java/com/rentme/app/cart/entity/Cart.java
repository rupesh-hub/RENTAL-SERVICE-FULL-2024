package com.rentme.app.cart.entity;

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
@Table(name = "_product_cart")
@EntityListeners(AuditingEntityListener.class)
@NamedQueries({
        @NamedQuery(name = "Cart.findByProductIdAndUsername",
                query = "SELECT c FROM Cart c WHERE c.productId = :productId AND c.username = :username"),
        @NamedQuery(name = "Cart.findByUsername",
                query = "SELECT c FROM Cart c WHERE c.username = :username")
})
public class Cart {

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

    private String productId;
    private String username;
    private int quantity;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime modifiedOn;

}