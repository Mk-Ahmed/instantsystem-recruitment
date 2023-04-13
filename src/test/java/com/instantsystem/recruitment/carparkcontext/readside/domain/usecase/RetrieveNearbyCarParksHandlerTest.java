package com.instantsystem.recruitment.carparkcontext.readside.domain.usecase;

import com.instantsystem.recruitment.carparkcontext.readside.domain.model.CarParkCapacity;
import com.instantsystem.recruitment.carparkcontext.readside.domain.model.CarParkInformation;
import com.instantsystem.recruitment.carparkcontext.readside.domain.model.CityNotHandledException;
import com.instantsystem.recruitment.carparkcontext.readside.domain.model.Coordinates;
import com.instantsystem.recruitment.carparkcontext.readside.domain.model.NearbyCarParksVm;
import com.instantsystem.recruitment.carparkcontext.readside.domain.usecase.RetrieveNearbyCarParksHandler.RetrieveNearbyCarParksQuery;
import com.instantsystem.recruitment.carparkcontext.readside.secondaryadapters.InMemoryCityParkingDataProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class RetrieveNearbyCarParksHandlerTest {

    private static final int DEFAULT_RADIUS = 3;
    private RetrieveNearbyCarParksHandler handler;

    @BeforeEach
    void setup() {
        handler = new RetrieveNearbyCarParksHandler(
                Map.of(
                        "Paris", new InMemoryCityParkingDataProvider(
                                Map.of(
                                        new CarParkInformation("Opera", new Coordinates(1, 1)), new CarParkCapacity(10, 4),
                                        new CarParkInformation("Chatelet", new Coordinates(2, 4)), new CarParkCapacity(20, 12)
                                )
                        ),
                        "Nice", new InMemoryCityParkingDataProvider(
                                Map.of(
                                        new CarParkInformation("Promenade", new Coordinates(4, 4)), new CarParkCapacity(10, 4),
                                        new CarParkInformation("Centre", new Coordinates(42, 44)), new CarParkCapacity(20, 12)
                                )
                        )));
    }

    @Test
    void should_reject_request_when_city_not_handled() {
        assertThatThrownBy(() -> handler.execute(RetrieveNearbyCarParksQuery.builder()
                .city("Nancy")
                .userCoordinates(new Coordinates(1, 1))
                .radius(DEFAULT_RADIUS)
                .build()))
                .isInstanceOf(CityNotHandledException.class);
    }

    @Test
    void should_return_car_parks_data_about_provided_country() {
        final var query = RetrieveNearbyCarParksQuery.builder()
                .city("Paris")
                .userCoordinates(new Coordinates(1, 1))
                .radius(DEFAULT_RADIUS)
                .build();

        final var actual = handler.execute(query);

        assertThat(actual).isEqualTo(new NearbyCarParksVm(Collections.emptySet()));
    }
}
