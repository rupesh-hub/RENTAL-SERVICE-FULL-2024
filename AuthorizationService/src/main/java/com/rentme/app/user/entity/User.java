package com.rentme.app.user.entity;

import com.rentme.app.address.entity.Address;
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
@NamedQueries(
        {
                @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE LOWER(u.email) = LOWER(:email)"),
                @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
                @NamedQuery(name = "User.findByUserId", query = "SELECT u FROM User u WHERE u.userId = :userId")
        }
)
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

    @Column(name = "user_id", nullable = false, unique = true)
    private String userId;

    private String firstName;
    private String lastName;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    private String password;
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    public String fullName() {
        return firstName + " " + lastName;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

}
