package com.rentme.app.security.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="_grant_types")
@DynamicUpdate
public class GrantType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "_grant_type_seq_generator")
    @SequenceGenerator(
            name = "_grant_type_seq_generator",
            sequenceName = "_grant_type_seq_generator",
            initialValue = 1,
            allocationSize = 1
    )
    @Column(name="id", nullable = false, updatable = false, unique = true)
    private Long id;

    private String grantType;

    @ManyToOne
    private Client client;

    public static GrantType from(AuthorizationGrantType authGrantType, Client client) {
        return GrantType
                .builder()
                .grantType(authGrantType.getValue())
                .client(client)
                .build();
    }

}
