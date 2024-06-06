package com.rentme.app.security.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="_authentication_methods")
@DynamicUpdate
public class AuthenticationMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "_authentication_method_seq_generator")
    @SequenceGenerator(
            name = "_authentication_method_seq_generator",
            sequenceName = "_authentication_method_seq_generator",
            initialValue = 1,
            allocationSize = 1
    )
    @Column(name="id", nullable = false, updatable = false, unique = true)
    private Long id;

    private String authenticationMethod;

    @ManyToOne
    private Client client;

    public static AuthenticationMethod from(ClientAuthenticationMethod clientAuthMethod,
                                            Client client){
        AuthenticationMethod authMethod = new AuthenticationMethod();
        authMethod.setAuthenticationMethod(clientAuthMethod.getValue());
        authMethod.setClient(client);
        return authMethod;
    }

}
