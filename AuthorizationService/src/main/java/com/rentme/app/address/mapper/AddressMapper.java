package com.rentme.app.address.mapper;

import com.rentme.app.address.entity.Address;
import com.rentme.app.address.model.AddressRequest;
import com.rentme.app.address.model.AddressResponse;

public final class AddressMapper {

    private AddressMapper() {
    }

    public static Address toEntity(AddressRequest request) {
        return Address
                .builder()
                .country(request.country())
                .province(request.province())
                .district(request.district())
                .city(request.city())
                .street(request.street())
                .zip(request.zip())
                .build();
    }

    public static AddressResponse toResponse(Address address) {
        return new AddressResponse(address.getId(),
                address.getCountry(),
                address.getProvince(),
                address.getDistrict(),
                address.getCity(),
                address.getStreet(),
                address.getZip());
    }

}
