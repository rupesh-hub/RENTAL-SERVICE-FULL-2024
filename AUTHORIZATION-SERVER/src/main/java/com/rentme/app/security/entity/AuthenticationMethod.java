package com.rentme.app.security.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "_authentication_methods")
@DynamicUpdate
public class AuthenticationMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "_authentication_method_id_seq_generator")
    @SequenceGenerator(name = "_authentication_method_id_seq_generator", sequenceName = "_authentication_method_id_seq_generator", allocationSize = 1, initialValue = 1)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(name = "authentication_method", unique = true, nullable = false)
    private String authenticationMethod;

    @ManyToMany(mappedBy = "authenticationMethods")
    @JsonIgnore
    private List<Client> clients;

    public AuthenticationMethod(String authenticationMethod){
        this.authenticationMethod = authenticationMethod;
    }

}