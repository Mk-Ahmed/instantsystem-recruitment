package com.instantsystem.recruitment.carparkcontext.readside.secondaryadapters;

import com.instantsystem.recruitment.carparkcontext.readside.domain.model.CarPark;
import com.instantsystem.recruitment.carparkcontext.readside.domain.model.Coordinates;
import com.instantsystem.recruitment.carparkcontext.readside.domain.port.CityParkingDataProvider;
import lombok.AllArgsConstructor;

import java.util.Set;

@AllArgsConstructor
public class StubParkingDataProvider implements CityParkingDataProvider {

    private final Set<CarPark> data;

    @Override
    public Set<CarPark> getCarParks(Coordinates userCoordinates, double radius) {
        return data;
    }
}
