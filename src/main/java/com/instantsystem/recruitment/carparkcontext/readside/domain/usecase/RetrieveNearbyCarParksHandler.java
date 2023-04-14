package com.instantsystem.recruitment.carparkcontext.readside.domain.usecase;

import com.instantsystem.recruitment.carparkcontext.readside.domain.model.CarPark;
import com.instantsystem.recruitment.carparkcontext.readside.domain.model.CityNotHandledException;
import com.instantsystem.recruitment.carparkcontext.readside.domain.model.Coordinates;
import com.instantsystem.recruitment.carparkcontext.readside.domain.port.CityCarParkDataProvider;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
public class RetrieveNearbyCarParksHandler {

    private final Map<String, CityCarParkDataProvider> cities;

    public Set<CarPark> execute(RetrieveNearbyCarParksQuery query) {
        final var dataProvider = getCityDataProvider(query);
        return dataProvider.getParParksAround(query.userCoordinates(), query.radius());
    }

    private CityCarParkDataProvider getCityDataProvider(RetrieveNearbyCarParksQuery query) {
        final var cityCarParkDataProvider = cities.get(query.city());
        if (cityCarParkDataProvider == null) throw new CityNotHandledException();
        return cityCarParkDataProvider;
    }

    @Builder
    public record RetrieveNearbyCarParksQuery(String city, Coordinates userCoordinates, double radius) {

    }

}
