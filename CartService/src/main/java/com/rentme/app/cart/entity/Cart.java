package com.rentme.app.cart.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
                query = "SELECT c FROM Cart c WHERE c.productId = :productId AND c.userId = :userId"),
        @NamedQuery(name = "Cart.findByUserId",
                query = "SELECT c FROM Cart c WHERE c.userId = :userId")
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
    private String userId;
    private int quantity;

}