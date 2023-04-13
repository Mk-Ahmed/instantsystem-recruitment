package com.instantsystem.recruitment.carparkcontext.readside.domain.usecase;

import com.instantsystem.recruitment.carparkcontext.readside.domain.model.CityNotHandledException;
import com.instantsystem.recruitment.carparkcontext.readside.domain.model.Coordinates;
import com.instantsystem.recruitment.carparkcontext.readside.domain.model.NearbyCarParksVm;
import com.instantsystem.recruitment.carparkcontext.readside.domain.port.CityParkingDataProvider;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.Map;

@RequiredArgsConstructor
public class RetrieveNearbyCarParksHandler {

    private final Map<String, CityParkingDataProvider> cities;

    public NearbyCarParksVm execute(RetrieveNearbyCarParksQuery query) {
        final CityParkingDataProvider cityParkingDataProvider = cities.get(query.city());
        if (cityParkingDataProvider == null) throw new CityNotHandledException();
        return new NearbyCarParksVm(Collections.emptySet());
    }

    @Builder
    public record RetrieveNearbyCarParksQuery(String city, Coordinates userCoordinates, int radius) {

    }
}
