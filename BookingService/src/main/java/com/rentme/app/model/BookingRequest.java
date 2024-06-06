package com.rentme.app.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class BookingRequest {

    private Long productId;
    private LocalDateTime bookingDate;
    private LocalDateTime returnDate;

}