package com.instantsystem.recruitment.carparkcontext.readside.secondaryadapters;

import com.instantsystem.recruitment.carparkcontext.readside.domain.model.NearbyCarParksVm;
import com.instantsystem.recruitment.carparkcontext.readside.domain.port.ParksDataProvider;

public class InMemoryParksDataProvider implements ParksDataProvider {


    private final NearbyCarParksVm data;

    public InMemoryParksDataProvider(NearbyCarParksVm data) {
        this.data = data;
    }

    @Override
    public NearbyCarParksVm get() {
        return data;
    }

}
