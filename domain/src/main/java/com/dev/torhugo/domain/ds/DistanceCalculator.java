package com.dev.torhugo.domain.ds;

public class DistanceCalculator {
    private DistanceCalculator() {}

    /**
     * 1. Converting Coordinates to Radians: <br/>
     *  1.1 Converts the differences in latitude and longitude between two points from degrees to radians using Math.toRadians(). <br/>
     * 2. Applying Haversine's Formula: <br/>
     *  2.1 Calculates intermediate values (a and c) using the Haversine formula, which considers the spherical shape of the Earth. <br/>
     * 3. Returns Distance in Kilometers: <br/>
     *  3.1 Multiplies the Earth's radius by the calculated central angle (c) to obtain the distance in kilometers. <br/>
     *
     * @param startLatitude The initial latitude of a point in degrees (Double).
     * @param startLongitude The initial longitude of a point in degrees (Double).
     * @param endLatitude The ending latitude of a point in degrees (Double).
     * @param endLongitude The ending longitude of a point in degrees (Double).
     * @return The calculated distance between the two points in kilometers (Double).
     * @throws IllegalArgumentException if any of the latitude or longitude values are outside the valid range (-90 to 90 for latitude and -180 to 180 for longitude).
     */
    public static Double calculateDistance(final Double startLatitude,
                                           final Double startLongitude,
                                           final Double endLatitude,
                                           final Double endLongitude) {
        final var EARTH_RADIUS_KM = 6371.01;
        final var diffLatitude = Math.toRadians(endLatitude - startLatitude);
        final var diffLongitude = Math.toRadians(endLongitude - startLongitude);
        final var a = Math.sin(diffLatitude / 2) * Math.sin(diffLatitude / 2) +
                Math.cos(Math.toRadians(startLatitude)) * Math.cos(Math.toRadians(endLatitude)) *
                        Math.sin(diffLongitude / 2) * Math.sin(diffLongitude / 2);
        final var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS_KM * c;
    }
}
