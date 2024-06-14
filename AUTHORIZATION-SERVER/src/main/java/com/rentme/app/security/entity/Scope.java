package com.rentme.app.security.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "_scopes")
public class Scope {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "_scope_id_seq_generator")
    @SequenceGenerator(name = "_scope_id_seq_generator", sequenceName = "_scope_id_seq_generator", allocationSize = 1, initialValue = 1)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(name="scope", unique = true, nullable = false)
    private String scope;

    @ManyToMany(mappedBy = "scopes")
    @JsonIgnore
    private List<Client> clients;

    public Scope(String scope) {
        this.scope = scope;
    }

}