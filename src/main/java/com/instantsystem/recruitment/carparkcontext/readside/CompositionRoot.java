package com.instantsystem.recruitment.carparkcontext.readside;

import com.instantsystem.recruitment.carparkcontext.readside.domain.model.NearbyCarParksVm;
import com.instantsystem.recruitment.carparkcontext.readside.domain.usecase.RetrieveNearbyCarParksHandler;
import com.instantsystem.recruitment.carparkcontext.readside.secondaryadapters.InMemoryParksDataProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class CompositionRoot {

    @Bean
    public RetrieveNearbyCarParksHandler retrieveNearbyCarParksHandler() {
        return new RetrieveNearbyCarParksHandler(
                Map.of("Paris", new InMemoryParksDataProvider(new NearbyCarParksVm("Paris"))));
    }
}
