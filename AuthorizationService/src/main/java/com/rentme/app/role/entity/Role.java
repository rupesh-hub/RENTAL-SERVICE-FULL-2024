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
@NamedQueries(
        {
                @NamedQuery(name = "Role.findByRole", query = "SELECT r FROM Role r WHERE p.role = :role"),
                @NamedQuery(name = "Role.findByRoleId", query = "SELECT r FROM Role r WHERE p.role_id = :roleId")
        }
)
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

    @Column(name = "role_id", nullable = false, unique = true)
    private String roleId;
    private String role;

    @ManyToOne
    private User user;

}
