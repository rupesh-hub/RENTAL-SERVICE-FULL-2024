package com.rentme.app.role.entity;

import com.rentme.app.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "_roles")
@DynamicUpdate
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "_role_seq_generator")
    @SequenceGenerator(
            name = "_role_seq_generator",
            sequenceName = "_role_seq_generator",
            initialValue = 1,
            allocationSize = 1
    )
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private Long id;

    private String role;

    @ManyToOne
    private User user;

}
