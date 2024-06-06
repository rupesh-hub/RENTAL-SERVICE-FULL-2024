package com.rentme.app.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rentme.app.enumeration.EventType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnalyticsEventResponse {

    private Long id;
    @Enumerated(EnumType.STRING)
    private EventType eventType;
    private String userId;
    private String productId;
    private LocalDateTime timestamp;
    private String details;

}
