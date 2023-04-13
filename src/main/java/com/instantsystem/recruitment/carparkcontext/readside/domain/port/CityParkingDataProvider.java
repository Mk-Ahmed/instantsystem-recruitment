package com.instantsystem.recruitment.carparkcontext.readside.domain.port;

import com.instantsystem.recruitment.carparkcontext.readside.domain.model.CarParkCapacity;
import com.instantsystem.recruitment.carparkcontext.readside.domain.model.CarParkInformation;

import java.util.Set;

public interface CityParkingDataProvider {

    Set<CarParkInformation> getParks();

    CarParkCapacity getCapacity(CarParkInformation carPark);

}
