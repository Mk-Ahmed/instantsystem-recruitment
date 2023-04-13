package com.instantsystem.recruitment.carparkcontext.readside.primaryadapters;

import com.instantsystem.recruitment.carparkcontext.readside.domain.model.CityNotHandledException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Map;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(CityNotHandledException.class)
    public ResponseEntity<?> handle(CityNotHandledException exception) {
        return new ResponseEntity<>(
                Map.of("error_code", exception.getErrorCode()),
                HttpStatus.NOT_FOUND);
    }
}
