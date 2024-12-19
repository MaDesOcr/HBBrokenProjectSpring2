package com.rents.services;

import java.io.IOException;
import java.util.List;

import com.rents.dtos.RentalRequest;
import com.rents.dtos.RentalResponse;
import com.rents.exceptions.AlreadyExistException;
import com.rents.exceptions.FormatNotSupportedException;
import com.rents.exceptions.NotFoundException;

public interface RentalInterface {
    RentalResponse createRental(RentalRequest rentalRequest) throws AlreadyExistException, NotFoundException, IOException, FormatNotSupportedException;

    List<RentalResponse> getRentals();

    RentalResponse getRental(Integer id) throws NotFoundException;

    RentalResponse updateRental(Integer id, RentalRequest rentalRequest) throws NotFoundException;
}
