package com.instantsystem.recruitment.carparkcontext.readside.secondaryadapters;

import com.instantsystem.recruitment.carparkcontext.readside.domain.model.CarPark;
import com.instantsystem.recruitment.carparkcontext.readside.domain.model.Coordinates;
import com.instantsystem.recruitment.carparkcontext.readside.domain.port.CityCarParkDataProvider;
import lombok.AllArgsConstructor;

import java.util.Set;

@AllArgsConstructor
public class StubCarParkDataProvider implements CityCarParkDataProvider {

    private final Set<CarPark> data;

    @Override
    public Set<CarPark> getParParksAround(Coordinates userCoordinates, double radius) {
        return data;
    }
}
