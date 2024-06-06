package com.rentme.app.product.entity;

import com.rentme.app.category.entity.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_product")
@EntityListeners(AuditingEntityListener.class)
@NamedQueries({
        @NamedQuery(name = "Product.findByCategory", query = "SELECT p FROM Product p WHERE p.category = :category")
})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "_product_sequence_gen")
    @SequenceGenerator(name = "_product_sequence_gen", sequenceName = "_product_sequence_gen", initialValue = 1, allocationSize = 1)
    @Column(name="id", nullable = false, updatable = false, unique = true)
    private Long id;

    private String name;
    private String description;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Category category;

    private double price;

    private LocalDateTime bookedAt;
    private LocalDateTime bookedTill;
    private String[] images;

}
