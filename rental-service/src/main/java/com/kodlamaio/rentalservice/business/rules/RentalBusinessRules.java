package com.kodlamaio.rentalservice.business.rules;

import com.kodlamaio.commonpackage.utils.exceptions.BusinessException;
import com.kodlamaio.rentalservice.api.clients.CarClient;
import com.kodlamaio.rentalservice.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RentalBusinessRules {

    private final RentalRepository repository;
    private final CarClient client;

    public void checkIfRentalExists(UUID id) {
        if (!repository.existsById(id)) {
            throw new BusinessException("MODEL_NOT_EXISTS");
        }
    }

    public void ensureCarIsAvailable(UUID carId) {
        var response = client.checkIfCarAvailable(carId);
        if (!response.isSuccess()) {
            throw new BusinessException(response.getMessage());
        }
    }
}
