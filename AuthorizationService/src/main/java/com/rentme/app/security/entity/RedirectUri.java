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
@Table(name="_redirect_uris")
@DynamicUpdate
public class RedirectUri {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "_redirect_uri_seq_generator")
    @SequenceGenerator(
            name = "_redirect_uri_seq_generator",
            sequenceName = "_redirect_uri_seq_generator",
            initialValue = 1,
            allocationSize = 1
    )
    @Column(name="id", nullable = false, updatable = false, unique = true)
    private Long id;

    private String url;

    @ManyToOne
    private Client client;

    public static RedirectUri from(String uri, Client client) {
        return RedirectUri
                .builder()
                .url(uri)
                .client(client)
                .build();
    }

}
