package com.instantsystem.recruitment.carparkcontext.readside.primaryadapters;

import com.instantsystem.recruitment.carparkcontext.readside.domain.usecase.RetrieveNearbyCarParksHandler;
import com.instantsystem.recruitment.carparkcontext.readside.domain.usecase.RetrieveNearbyCarParksHandler.RetrieveNearbyCarParksQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/parks")
@RequiredArgsConstructor
public class CarParkController {

    private final RetrieveNearbyCarParksHandler retrieveNearbyCarParksHandler;

    @GetMapping
    public Object get(@RequestParam String city) {
        final var query = new RetrieveNearbyCarParksQuery(city);
        return retrieveNearbyCarParksHandler.execute(query);
    }
}
