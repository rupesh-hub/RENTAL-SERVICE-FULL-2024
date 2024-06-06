package com.rentme.app.model;

import com.rentme.app.enumeration.EventType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AnalyticsEventRequest {

    @Enumerated(EnumType.STRING)
    private EventType eventType;
    private String userId;
    private String productId;
    private String details;

}
