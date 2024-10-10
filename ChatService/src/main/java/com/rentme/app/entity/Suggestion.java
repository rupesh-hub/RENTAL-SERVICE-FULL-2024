package com.rentme.app.entity;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Suggestion {
    private Long id;
    private User user;
}
