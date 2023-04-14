package com.instantsystem.recruitment.carparkcontext.readside;

import com.instantsystem.recruitment.carparkcontext.readside.domain.usecase.RetrieveNearbyCarParksHandler;
import com.instantsystem.recruitment.carparkcontext.readside.secondaryadapters.HttpPoitiersCityCarParkDataProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class CompositionRoot {

    @Bean
    public RetrieveNearbyCarParksHandler retrieveNearbyCarParksHandler() {
        return new RetrieveNearbyCarParksHandler(
                Map.of(
                        "Poitiers", new HttpPoitiersCityCarParkDataProvider()
                )
        );
    }

}
