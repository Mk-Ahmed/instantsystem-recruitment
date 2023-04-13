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

    public NearbyCarParksVm execute(RetrieveNearbyCarParksQuery query) {
        final CityParkingDataProvider cityParkingDataProvider = cities.get(query.city());
        if (cityParkingDataProvider == null) throw new CityNotHandledException();
        final var carParks = cityParkingDataProvider.getCarParks(query.userCoordinates(), query.radius());
        return new NearbyCarParksVm(carParks);
    }

    @Builder
    public record RetrieveNearbyCarParksQuery(String city, Coordinates userCoordinates, int radius) {

    }

    public record NearbyCarParksVm(Set<CarPark> parks) {


    }
}
