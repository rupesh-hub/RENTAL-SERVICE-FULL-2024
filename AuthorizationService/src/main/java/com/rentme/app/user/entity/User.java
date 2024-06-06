package com.rentme.app.user.entity;

import com.rentme.app.role.entity.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "_users")
@DynamicUpdate
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "_user_seq_generator")
    @SequenceGenerator(
            name = "_user_seq_generator",
            sequenceName = "_user_seq_generator",
            initialValue = 1,
            allocationSize = 1
    )
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private Long id;

    private String username;
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Role> roles;


}
