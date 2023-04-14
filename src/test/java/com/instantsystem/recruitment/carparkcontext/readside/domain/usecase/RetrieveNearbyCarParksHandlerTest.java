package com.instantsystem.recruitment.carparkcontext.readside.domain.usecase;

import com.instantsystem.recruitment.carparkcontext.readside.domain.model.Capacity;
import com.instantsystem.recruitment.carparkcontext.readside.domain.model.CarPark;
import com.instantsystem.recruitment.carparkcontext.readside.domain.model.CityNotHandledException;
import com.instantsystem.recruitment.carparkcontext.readside.domain.model.Coordinates;
import com.instantsystem.recruitment.carparkcontext.readside.domain.usecase.RetrieveNearbyCarParksHandler.RetrieveNearbyCarParksQuery;
import com.instantsystem.recruitment.carparkcontext.readside.secondaryadapters.StubCarParkDataProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class RetrieveNearbyCarParksHandlerTest {

    private static final int DEFAULT_RADIUS = 3;
    private RetrieveNearbyCarParksHandler handler;

    @BeforeEach
    void setup() {
        handler = new RetrieveNearbyCarParksHandler(
                Map.of(
                        "Paris", new StubCarParkDataProvider(
                                Set.of(
                                        new CarPark("Opera", 10, new Coordinates(1, 1), new Capacity(10, 4)),
                                        new CarPark("Chatelet", 14, new Coordinates(2, 2), new Capacity(20, 12))
                                )),

                        "Nice", new StubCarParkDataProvider(
                                Set.of(
                                        new CarPark("Promenade", 10, new Coordinates(3, 3), new Capacity(17, 6)),
                                        new CarPark("Chatelet", 14, new Coordinates(4, 4), new Capacity(30, 17))
                                ))
                ));
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

        assertThat(actual).isEqualTo(Set.of(
                new CarPark("Opera", 10, new Coordinates(1, 1), new Capacity(10, 4)),
                new CarPark("Chatelet", 14, new Coordinates(2, 2), new Capacity(20, 12))
        ));
    }
}
