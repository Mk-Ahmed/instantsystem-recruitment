package com.instantsystem.recruitment.carparkcontext.readside.domain.usecase;

import com.instantsystem.recruitment.carparkcontext.readside.domain.model.CityNotHandledException;
import com.instantsystem.recruitment.carparkcontext.readside.domain.model.NearbyCarParksVm;
import com.instantsystem.recruitment.carparkcontext.readside.domain.usecase.RetrieveNearbyCarParksHandler.RetrieveNearbyCarParksQuery;
import com.instantsystem.recruitment.carparkcontext.readside.secondaryadapters.InMemoryParksDataProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class RetrieveNearbyCarParksHandlerTest {

    private RetrieveNearbyCarParksHandler handler;

    @BeforeEach
    void setup() {
        handler = new RetrieveNearbyCarParksHandler(
                Map.of(
                        "Paris", new InMemoryParksDataProvider(new NearbyCarParksVm("Paris")),
                        "Marseille", new InMemoryParksDataProvider(new NearbyCarParksVm("Marseille"))
                ));
    }

    @Test
    void should_reject_request_when_city_not_handled() {
        assertThatThrownBy(() -> {
            final var query = new RetrieveNearbyCarParksQuery("Nancy");
            handler.execute(query);
        }).isInstanceOf(CityNotHandledException.class);
    }

    @Test
    void should_return_car_parks_data_about_provided_country() {
        final var query = new RetrieveNearbyCarParksQuery("Paris");

        final var actual = handler.execute(query);

        assertThat(actual).isEqualTo(new NearbyCarParksVm("Paris"));
    }
}
