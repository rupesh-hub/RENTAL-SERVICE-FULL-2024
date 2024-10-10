package com.rentme.app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name="USERS")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_ID_SEQUENCE_GENERATOR")
    @SequenceGenerator(name = "USER_ID_SEQUENCE_GENERATOR", sequenceName = "USER_ID_SEQUENCE_GENERATOR", allocationSize = 50, initialValue = 1)
    private Long id;

    private String username;
    private String email;
    private String profile;
    private String firstName;
    private String lastName;
    private LocalDateTime lastLoginDate;
    private LocalDateTime logoutDate;

}
