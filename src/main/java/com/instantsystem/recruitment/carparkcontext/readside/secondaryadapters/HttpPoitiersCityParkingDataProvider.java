package com.instantsystem.recruitment.carparkcontext.readside.secondaryadapters;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.instantsystem.recruitment.carparkcontext.readside.domain.model.Capacity;
import com.instantsystem.recruitment.carparkcontext.readside.domain.model.CarPark;
import com.instantsystem.recruitment.carparkcontext.readside.domain.model.Coordinates;
import com.instantsystem.recruitment.carparkcontext.readside.domain.port.CityParkingDataProvider;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.instantsystem.recruitment.carparkcontext.readside.secondaryadapters.DistanceProvider.computeDistanceInKm;

public final class HttpPoitiersCityParkingDataProvider implements CityParkingDataProvider {
    private static final String CAR_PARKS_CAPACITY_API_URL = "https://data.grandpoitiers.fr/api/records/1.0/search/?dataset=mobilites-stationnement-des-parkings-en-temps-reel&facet=nom";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final HttpClient HTTP_CLIENT = HttpClient.newBuilder().build();

    @Override
    public Set<CarPark> getCarParks(Coordinates userCoordinates, double radius) {
        return httpGet(CAR_PARKS_CAPACITY_API_URL, CarParkCapacityApiResult[].class)
                .stream()
                .map(data -> data.fields)
                .filter(data -> data.geo_point_2d != null)
                .map(buildCarPark(userCoordinates))
                .filter(isInRadius(radius))
                .collect(Collectors.toSet());
    }

    private static Predicate<CarPark> isInRadius(double radius) {
        return carPark -> carPark.distance() <= radius;
    }

    private static Function<CarParkCapacityApiResult.Fields, CarPark> buildCarPark(Coordinates userCoordinates) {
        return carPark -> {
            final var carParkCoordinates = new Coordinates(carPark.geo_point_2d[0], carPark.geo_point_2d[1]);
            final var distance = computeDistanceInKm(userCoordinates, carParkCoordinates);
            final var capacity = new Capacity(carPark.capacite, carPark.places);
            return new CarPark(carPark.nom, distance, carParkCoordinates, capacity);
        };
    }

    private static <T> List<T> httpGet(String url, Class<T[]> valueType) {
        try {
            final var httpRequest = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
            final var bodyHandler = HttpResponse.BodyHandlers.ofString();
            final var response = HTTP_CLIENT.send(httpRequest, bodyHandler);
            final var records = OBJECT_MAPPER.readTree(response.body()).get("records");
            return Arrays.asList(OBJECT_MAPPER.treeToValue(records, valueType));
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private record CarParkCapacityApiResult(@JsonProperty Fields fields) {

        @JsonIgnoreProperties(ignoreUnknown = true)
        private record Fields(@JsonProperty String nom,
                              @JsonProperty double[] geo_point_2d,
                              @JsonProperty int capacite,
                              @JsonProperty int places) {

        }

    }
}
