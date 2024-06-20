package com.rentme.app.entity;

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
        @NamedQuery(name = "Cart.findByName",
                query = "SELECT I FROM Inventory I WHERE I.name = :name")
})
public class Inventory {

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

    private String name;
    private String description;
    private int quantity;
    private double price;
}
