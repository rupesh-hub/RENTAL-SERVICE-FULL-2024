package com.rentme.app.address.entity;

import com.rentme.app.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "_user_address")
@DynamicUpdate
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "_user_address_seq_generator")
    @SequenceGenerator(
            name = "_user_address_seq_generator",
            sequenceName = "_user_address_seq_generator",
            initialValue = 1,
            allocationSize = 1
    )
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private Long id;

    private String country;
    private String province;
    private String district;
    private String city;
    private String street;
    private long zip;

    @OneToOne(mappedBy = "address")
    private User user;

}
