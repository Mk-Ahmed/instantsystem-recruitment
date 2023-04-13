package com.instantsystem.recruitment.carparkcontext.readside.domain.usecase;

import com.instantsystem.recruitment.carparkcontext.readside.domain.model.CarPark;
import com.instantsystem.recruitment.carparkcontext.readside.domain.model.CityNotHandledException;
import com.instantsystem.recruitment.carparkcontext.readside.domain.model.Coordinates;
import com.instantsystem.recruitment.carparkcontext.readside.domain.port.CityParkingDataProvider;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
public class RetrieveNearbyCarParksHandler {

    private final Map<String, CityParkingDataProvider> cities;

    public Set<CarPark> execute(RetrieveNearbyCarParksQuery query) {
        final CityParkingDataProvider cityParkingDataProvider = cities.get(query.city());
        if (cityParkingDataProvider == null) throw new CityNotHandledException();
        return cityParkingDataProvider.getCarParks(query.userCoordinates(), query.radius());
    }

    @Builder
    public record RetrieveNearbyCarParksQuery(String city, Coordinates userCoordinates, double radius) {

    }

}
