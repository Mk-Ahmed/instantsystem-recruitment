package com.instantsystem.recruitment.carparkcontext.readside.domain.port;

import com.instantsystem.recruitment.carparkcontext.readside.domain.model.CarPark;
import com.instantsystem.recruitment.carparkcontext.readside.domain.model.Coordinates;

import java.util.Set;

public interface CityParkingDataProvider {

    Set<CarPark> getCarParks(Coordinates userCoordinates, int radius);

}
