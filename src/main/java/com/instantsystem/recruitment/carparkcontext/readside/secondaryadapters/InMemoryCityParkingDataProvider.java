package com.instantsystem.recruitment.carparkcontext.readside.secondaryadapters;

import com.instantsystem.recruitment.carparkcontext.readside.domain.model.CarParkCapacity;
import com.instantsystem.recruitment.carparkcontext.readside.domain.model.CarParkInformation;
import com.instantsystem.recruitment.carparkcontext.readside.domain.port.CityParkingDataProvider;
import lombok.AllArgsConstructor;

import java.util.Map;
import java.util.Set;

@AllArgsConstructor
public class InMemoryCityParkingDataProvider implements CityParkingDataProvider {

    private final Map<CarParkInformation, CarParkCapacity> data;

    @Override
    public Set<CarParkInformation> getParks() {
        return data.keySet();
    }

    @Override
    public CarParkCapacity getCapacity(CarParkInformation carPark) {
        return data.get(carPark);
    }

}
