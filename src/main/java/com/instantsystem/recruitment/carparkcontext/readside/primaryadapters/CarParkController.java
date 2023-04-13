package com.instantsystem.recruitment.carparkcontext.readside.primaryadapters;

import com.instantsystem.recruitment.carparkcontext.readside.domain.model.NearbyCarParksVm;
import com.instantsystem.recruitment.carparkcontext.readside.domain.usecase.RetrieveNearbyCarParksHandler;
import com.instantsystem.recruitment.carparkcontext.readside.domain.usecase.RetrieveNearbyCarParksHandler.RetrieveNearbyCarParksQuery;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/parks")
@RequiredArgsConstructor
public class CarParkController {

    private final RetrieveNearbyCarParksHandler retrieveNearbyCarParksHandler;

    @GetMapping
    @ApiResponse(responseCode = "200", description = "Return the nearby parks",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = NearbyCarParksVm.class)))
    @ApiResponse(responseCode = "404", description = "City not handled",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    public Object get(@RequestParam String city) {
        final var query = new RetrieveNearbyCarParksQuery(city);
        return retrieveNearbyCarParksHandler.execute(query);
    }
}
