package com.instantsystem.recruitment.carparkcontext.readside.domain.usecase;

import com.instantsystem.recruitment.carparkcontext.readside.domain.model.CityNotHandledException;
import com.instantsystem.recruitment.carparkcontext.readside.domain.model.NearbyCarParksVm;
import com.instantsystem.recruitment.carparkcontext.readside.domain.port.ParksDataProvider;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class RetrieveNearbyCarParksHandler {

    private final Map<String, ParksDataProvider> dataProviders;

    public NearbyCarParksVm execute(RetrieveNearbyCarParksQuery query) {
        final ParksDataProvider parksDataProvider = dataProviders.get(query.city());
        if (parksDataProvider == null) throw new CityNotHandledException();
        return parksDataProvider.get();
    }

    public record RetrieveNearbyCarParksQuery(String city) {

    }
}
