package com.instantsystem.recruitment.carparkcontext.readside.domain.model;

import lombok.Builder;

@Builder
public record CarPark(String name, int distance,
                      Coordinates coordinates, Capacity capacity) {

}
