package com.kodlamaio.filterservice.api.controllers;

import com.kodlamaio.commonpackage.utils.dto.ChangeCarStateRequest;
import com.kodlamaio.filterservice.business.abstracts.FilterService;
import com.kodlamaio.filterservice.business.dto.responses.GetAllFiltersResponse;
import com.kodlamaio.filterservice.business.dto.responses.GetFilterResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/filters")
public class FiltersController {
    private final FilterService service;

    @GetMapping
    @Secured("ROLE_user")
    public List<GetAllFiltersResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @Secured("ROLE_user")
    public GetFilterResponse getByIId(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PutMapping("/change-car-state")
    public void changeCarState(@RequestBody ChangeCarStateRequest request) {
        service.changeStateByCarId(request.getCarId(), request.getCarState());
    }
}
