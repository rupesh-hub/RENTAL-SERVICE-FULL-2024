package com.rentme.app.review.entity;

import com.rentme.app.comment.entity.Comment;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "_product_review")
@EntityListeners(AuditingEntityListener.class)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "_product_review_seq_generator")
    @SequenceGenerator(
            name = "_product_review_seq_generator",
            sequenceName = "_product_review_seq_generator",
            initialValue = 1,
            allocationSize = 1
    )
    @Column(name="id", nullable = false, updatable = false, unique = true)
    private Long id;

    @OneToMany(mappedBy = "review")
    @JoinTable(
            name = "review_comments",
            joinColumns = @JoinColumn(name = "review_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "comment_id", nullable = false)
    )
    private Set<Comment> comments = new HashSet<>();

    private Long productId;
    private int rating;

}
