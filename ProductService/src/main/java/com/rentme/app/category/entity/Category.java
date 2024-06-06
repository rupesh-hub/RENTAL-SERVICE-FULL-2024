package com.rentme.app.category.entity;

import com.rentme.app.product.entity.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_product_category")
@EntityListeners(AuditingEntityListener.class)
@NamedQueries({
        @NamedQuery(name = "Category.findByName", query = "SELECT c FROM Category c WHERE c.name = :name"),
        @NamedQuery(name = "Category.findAllActive", query = "SELECT c FROM Category c WHERE c.active = true")
})
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "_product_category_sequence_gen")
    @SequenceGenerator(name = "_product_category_sequence_gen", sequenceName = "_product_category_sequence_gen", initialValue = 1, allocationSize = 1)
    @Column(name="id", nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(name="name", unique = true, nullable = false)
    private String name;

    private String description;

    @OneToMany(mappedBy = "category")
    @JoinTable(
            name = "products_category",
            joinColumns = @JoinColumn(name = "category_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "product_id", nullable = false)
    )
    private Set<Product> products = new HashSet<>();

    private boolean active;

}
