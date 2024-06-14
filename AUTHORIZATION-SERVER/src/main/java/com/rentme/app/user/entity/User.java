package com.rentme.app.user.entity;

import com.rentme.app.role.entity.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "_users")
@EntityListeners(AuditingEntityListener.class)
@NamedQueries(
        {
                @NamedQuery(name = "User.findByUsername", query = "SELECT U FROM User U WHERE U.username=:username"),
                @NamedQuery(name = "User.findByUserId", query = "SELECT U FROM User U WHERE U.userId=:userId"),
                @NamedQuery(name = "User.findByEmail", query = "SELECT U FROM User U WHERE U.email=:email")
        }
)
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "_user_id_seq_generator")
    @SequenceGenerator(name = "_user_id_seq_generator", sequenceName = "_user_id_seq_generator", allocationSize = 1, initialValue = 1)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private Long id;

    private String userId;
    private String firstName;
    private String lastName;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    private String email;
    private String password;

    @Transient
    private String confirmPassword;

    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "_users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime modifiedOn;

}