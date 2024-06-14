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
@Table(name = "_redirect_uris")
public class RedirectUri {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "_redirect_uri_id_seq_generator")
    @SequenceGenerator(name = "_redirect_uri_id_seq_generator", sequenceName = "_redirect_uri_id_seq_generator", allocationSize = 1, initialValue = 1)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(name="uri", unique = true, nullable = false)
    private String uri;

    @ManyToMany(mappedBy = "redirectUris")
    @JsonIgnore
    private List<Client> clients;

    public RedirectUri(String uri){
        this.uri = uri;
    }

}