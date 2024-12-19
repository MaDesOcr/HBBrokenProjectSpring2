package com.rents.mappers;

import org.springframework.stereotype.Component;

import com.rents.dtos.RentalResponse;
import com.rents.entities.Rental;

import java.util.function.Function;

// Unuseful for now, because we return all attrs. But could change.
@Component
public class RentalDTOMapper implements Function<Rental, RentalResponse> {
    @Override
    public RentalResponse apply(Rental rental) {
        return new RentalResponse(rental.getId(), rental.getName(), rental.getSurface(), rental.getPrice(), rental.getPicture(), rental.getDescription(), rental.getOwner().getId(), rental.getCreatedAt(), rental.getUpdatedAt());
    }
}
