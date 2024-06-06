package com.rentme.app.security.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="_scopes")
@DynamicUpdate
public class Scope {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "_scope_seq_generator")
    @SequenceGenerator(
            name = "_scope_seq_generator",
            sequenceName = "_scope_seq_generator",
            initialValue = 1,
            allocationSize = 1
    )
    @Column(name="id", nullable = false, updatable = false, unique = true)
    private Long id;

    private String scope;

    @ManyToOne
    private Client client;

    public static Scope from(String scope, Client client){
        return Scope
                .builder()
                .scope(scope)
                .client(client)
                .build();
    }
}
