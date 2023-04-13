package com.instantsystem.recruitment.carparkcontext.readside;

import com.instantsystem.recruitment.carparkcontext.readside.domain.usecase.RetrieveNearbyCarParksHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class CompositionRoot {

    @Bean
    public RetrieveNearbyCarParksHandler retrieveNearbyCarParksHandler() {
        return new RetrieveNearbyCarParksHandler(Collections.emptyMap());
    }

}
