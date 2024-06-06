package com.rentme.app.comment.entity;

import com.rentme.app.review.entity.Review;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "_product_comment")
@EntityListeners(AuditingEntityListener.class)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "_product_comment_seq_generator")
    @SequenceGenerator(
            name = "_product_comment_seq_generator",
            sequenceName = "_product_comment_seq_generator",
            initialValue = 1,
            allocationSize = 1
    )
    @Column(name="id", nullable = false, updatable = false, unique = true)
    private Long id;

    private String comment;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Review review;


}