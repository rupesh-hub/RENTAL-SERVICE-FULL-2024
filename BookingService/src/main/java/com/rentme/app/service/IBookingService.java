package com.rentme.app.service;

import com.rentme.app.model.BookingRequest;
import com.rentme.app.model.BookingResponse;
import com.rentme.app.util.GlobalResponse;

import java.security.Principal;
import java.util.List;

public interface IBookingService {

    GlobalResponse<Void> create(BookingRequest bookingRequest, Principal principal);

    GlobalResponse<BookingResponse> findById(Long id, Principal principal);

    GlobalResponse<List<BookingResponse>> findAll(int page, int size, Principal principal);

    GlobalResponse<Void> update(BookingRequest bookingRequest, Long id);

    GlobalResponse<Void> delete(Long id);

}