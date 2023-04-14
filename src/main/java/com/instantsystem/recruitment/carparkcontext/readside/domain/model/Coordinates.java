package com.instantsystem.recruitment.carparkcontext.readside.domain.model;

public record Coordinates(double latitude, double longitude) {

    // thanks to https://dzone.com/articles/distance-calculation-using-3 :)
    public double computeDistanceInMetre(Coordinates coordinates) {
        final double theta = longitude - coordinates.longitude();
        double dist = Math.sin(deg2rad(latitude))
                * Math.sin(deg2rad(coordinates.latitude()))
                + Math.cos(deg2rad(latitude))
                * Math.cos(deg2rad(coordinates.latitude()))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515 * 1.609344 * 1000;
        return dist;
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

}
