package com.instantsystem.recruitment.carparkcontext.readside.domain.model;

import lombok.Getter;

@Getter
public class CityNotHandledException extends RuntimeException {

    private final String errorCode;

    public CityNotHandledException() {
        super();
        this.errorCode = "city_not_handled";
    }
}
