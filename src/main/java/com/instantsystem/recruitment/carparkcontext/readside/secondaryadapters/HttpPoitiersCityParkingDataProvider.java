package com.instantsystem.recruitment.carparkcontext.readside.secondaryadapters;

import com.instantsystem.recruitment.carparkcontext.readside.domain.model.CarPark;
import com.instantsystem.recruitment.carparkcontext.readside.domain.model.Coordinates;
import com.instantsystem.recruitment.carparkcontext.readside.domain.port.CityParkingDataProvider;

import java.util.Collections;
import java.util.Set;

public final class HttpPoitiersCityParkingDataProvider implements CityParkingDataProvider {

    @Override
    public Set<CarPark> getCarParks(Coordinates userCoordinates, int radius) {
        return Collections.emptySet();
    }

}
