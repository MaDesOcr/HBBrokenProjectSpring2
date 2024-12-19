package com.rents.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.rents.dtos.RentalRequest;
import com.rents.dtos.RentalResponse;
import com.rents.exceptions.AlreadyExistException;
import com.rents.exceptions.FormatNotSupportedException;
import com.rents.exceptions.NotFoundException;
import com.rents.services.RentalService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Validated
@RequestMapping("/api")
public class RentalController {
    private final RentalService rentalService;



    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(value = "/rentals", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public RentalResponse createRental(@Valid @ModelAttribute RentalRequest rentalRequest) throws AlreadyExistException, NotFoundException, IOException, FormatNotSupportedException {
        return rentalService.createRental(rentalRequest);
    }

    @GetMapping("/rentals")
    public Map<String, List<RentalResponse>> getRentals() {
        List<RentalResponse> rentalList = rentalService.getRentals();
        Map<String, List<RentalResponse>> response = new HashMap<>();
        response.put("rentals", rentalList);
        return response;
    }

    @GetMapping("/rentals/{id}")
    public RentalResponse getRental(@PathVariable @Min(value = 1, message = "L'identifiant doit être égal ou supérieur à un (1).") int id) throws NotFoundException {
        return rentalService.getRental(id);
    }

    @PutMapping(value = "/rentals/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public RentalResponse updateRental(@PathVariable @Min(value = 1, message = "L'identifiant doit être égal ou supérieur à un (1).") int id, @Valid @ModelAttribute RentalRequest rentalRequest) throws NotFoundException {
        return rentalService.updateRental(id, rentalRequest);
    }
}
