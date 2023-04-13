package com.instantsystem.recruitment.carparkcontext.readside.secondaryadapters;

import com.instantsystem.recruitment.carparkcontext.readside.domain.model.Coordinates;

public class DistanceProvider {

    // thanks to https://dzone.com/articles/distance-calculation-using-3 :)
    static double computeDistanceInKm(Coordinates first, Coordinates second) {
        final double theta = first.longitude() - second.longitude();
        double dist = Math.sin(deg2rad(first.latitude()))
                * Math.sin(deg2rad(second.latitude()))
                + Math.cos(deg2rad(first.latitude()))
                * Math.cos(deg2rad(second.latitude()))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515 * 1.609344;
        return dist;
    }

    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private static double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

}
