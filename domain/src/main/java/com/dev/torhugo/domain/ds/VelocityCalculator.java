package com.dev.torhugo.domain.ds;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class VelocityCalculator {
    private VelocityCalculator() {}

    /**
     * 1. Calculating Elapsed Time in Seconds: <br/>
     *  1.1 Converts startTime and endTime to milliseconds considering the system's default time zone. <br/>
     *  1.2 Calculates the elapsed time in seconds by subtracting the start time in milliseconds from the end time in milliseconds and dividing by 1000. <br/>
     * 2. Calculating Average Speed: <br/>
     *  2.1 Calls the calculateAverageSpeed method with the distance and calculated elapsed time in seconds. <br/>
     *
     * @param distance The distance traveled in kilometers (Double).
     * @param startTime The initial time of the travel as a LocalDateTime object.
     * @param endTime The ending time of the travel as a LocalDateTime object.
     * @return The calculated average velocity in kilometers per hour (Double).
     * @throws NullPointerException if any of the parameters (distance, startTime, or endTime) are null.
     * @throws IllegalArgumentException if distance is less than or equal to zero.
     */
    public static Double calculateVelocity(final Double distance,
                                           final LocalDateTime startTime,
                                           final LocalDateTime endTime) {
        final var startTimeMillis = startTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        final var endTimeMillis = endTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        final var timeElapsedInSeconds = (endTimeMillis - startTimeMillis) / 1000.0;
        return calculateAverageSpeed(distance, timeElapsedInSeconds);
    }

    public static double calculateAverageSpeed(final Double distance,
                                               final Double time) {
        return distance / (time / 3600);
    }
}
