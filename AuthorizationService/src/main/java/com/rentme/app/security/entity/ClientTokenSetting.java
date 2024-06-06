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
@Table(name = "_client_token_settings")
@DynamicUpdate
public class ClientTokenSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "_client_token_setting_seq_generator")
    @SequenceGenerator(
            name = "_client_token_setting_seq_generator",
            sequenceName = "_client_token_setting_seq_generator",
            initialValue = 1,
            allocationSize = 1
    )
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private Long id;

    private int accessTokenTTL;

    private String tokenType;

    @OneToOne
    private Client client;
}
