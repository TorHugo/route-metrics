package entity;

import com.dev.torhugo.domain.entity.Position;
import org.junit.jupiter.api.Test;
import util.MessageUtil;

import java.time.LocalDateTime;
import java.util.UUID;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;

class PositionTest extends MessageUtil {

    @Test
    void shouldCalculateDistanceWhenThreeValidPointsAndStraightLine(){
        final var expectedDistanceAToB = 0.7272159617008673;
        final var expectedDistanceTotal = 1.3899387647184702;

        final var route = UUID.randomUUID();
        final var valueZero = 0.0;
        final var startLatitude = -22.9067;
        final var startLongitude = -43.1729;

        final var endLatitude = -22.90016;
        final var endLongitude = -43.1729;

        final var newLatitude = -22.8942;
        final var newLongitude = -43.1729;


        final var position = Position.create(route, startLatitude, startLongitude);
        assertNotNull(position, MESSAGE_NOT_NULL);
        assertEquals(valueZero, position.getMaxVelocity(), MESSAGE_TO_EQUAL);
        assertEquals(valueZero, position.getMinVelocity(), MESSAGE_TO_EQUAL);
        assertEquals(valueZero, position.getDistance(), MESSAGE_TO_EQUAL);

        position.calculateDistanceAndVelocity(endLatitude, endLongitude);
        assertNotNull(position, MESSAGE_NOT_NULL);
        assertNotNull(position.getMaxVelocity(), MESSAGE_NOT_NULL);
        assertNotNull(position.getMinVelocity(), MESSAGE_NOT_NULL);
        assertNotNull(position.getDistance(), MESSAGE_NOT_NULL);
        assertEquals(expectedDistanceAToB, position.getDistance(), MESSAGE_TO_EQUAL);

        position.updatePosition(endLatitude, endLongitude);
        assertNotEquals(position.getCoordinate().latitude(), startLatitude, MESSAGE_NOT_EQUAL);

        position.calculateDistanceAndVelocity(newLatitude, newLongitude);
        assertNotNull(position, MESSAGE_NOT_NULL);
        assertNotNull(position.getMaxVelocity(), MESSAGE_NOT_NULL);
        assertNotNull(position.getMinVelocity(), MESSAGE_NOT_NULL);
        assertNotNull(position.getDistance(), MESSAGE_NOT_NULL);
        assertEquals(expectedDistanceTotal, position.getDistance(), MESSAGE_TO_EQUAL);
    }

    @Test
    void shouldCalculateDistanceWhenThreeValidPointsAndNotStraightLine(){
        final var expectedDistanceAToB = 0.5003779552986967;
        final var expectedDistanceTotal = 1.0450240776585642;

        final var route = UUID.randomUUID();
        final var valueZero = 0.0;
        final var startLatitude = -22.9067;
        final var startLongitude = -43.1729;

        final var endLatitude = -22.9112;
        final var endLongitude = -43.1729;

        final var newLatitude = -22.9157;
        final var newLongitude = -43.1750;


        final var position = Position.create(route, startLatitude, startLongitude);
        assertNotNull(position, MESSAGE_NOT_NULL);
        assertEquals(valueZero, position.getMaxVelocity(), MESSAGE_TO_EQUAL);
        assertEquals(valueZero, position.getMinVelocity(), MESSAGE_TO_EQUAL);
        assertEquals(valueZero, position.getDistance(), MESSAGE_TO_EQUAL);

        position.calculateDistanceAndVelocity(endLatitude, endLongitude);
        assertNotNull(position, MESSAGE_NOT_NULL);
        assertNotNull(position.getMaxVelocity(), MESSAGE_NOT_NULL);
        assertNotNull(position.getMinVelocity(), MESSAGE_NOT_NULL);
        assertNotNull(position.getDistance(), MESSAGE_NOT_NULL);
        assertEquals(expectedDistanceAToB, position.getDistance(), MESSAGE_TO_EQUAL);

        position.updatePosition(endLatitude, endLongitude);
        assertNotEquals(position.getCoordinate().latitude(), startLatitude, MESSAGE_NOT_EQUAL);

        position.calculateDistanceAndVelocity(newLatitude, newLongitude);
        assertNotNull(position, MESSAGE_NOT_NULL);
        assertNotNull(position.getMaxVelocity(), MESSAGE_NOT_NULL);
        assertNotNull(position.getMinVelocity(), MESSAGE_NOT_NULL);
        assertNotNull(position.getDistance(), MESSAGE_NOT_NULL);
        assertEquals(expectedDistanceTotal, position.getDistance(), MESSAGE_TO_EQUAL);
    }

    @Test
    void shoudCalculateStartDistance() throws InterruptedException {
        final var route = UUID.randomUUID();
        final var valueZero = 0.0;
        final var startLatitude = -22.9067;
        final var startLongitude = -43.1729;

        final var endLatitude = -22.9112;
        final var endLongitude = -43.1729;

        final var newLatitude = -22.9158;
        final var newLongitude = -43.1750;

        final var position = Position.create(route, startLatitude, startLongitude);

        position.calculateDistanceAndVelocity(endLatitude, endLongitude);
        position.updatePosition(endLatitude, endLongitude);

        sleep(2000);
        position.calculateDistanceAndVelocity(newLatitude, newLongitude);
        position.updatePosition(newLatitude, newLongitude);

        assertNotNull(position, MESSAGE_NOT_NULL);
        assertNotEquals(valueZero, position.getDistance(), MESSAGE_NOT_EQUAL);
        assertNotEquals(valueZero, position.getMaxVelocity(), MESSAGE_NOT_EQUAL);
        assertNotEquals(valueZero, position.getMinVelocity(), MESSAGE_NOT_EQUAL);
    }

    @Test
    void shouldRestorePositionWithSuccess(){
        final var expectedPosition = UUID.randomUUID();
        final var expectedRoute = UUID.randomUUID();
        final var expectedLatitude = Math.random();
        final var expectedLongitude = Math.random();
        final var expectedTime = LocalDateTime.now();
        final var expectedDistance = Math.random();
        final var expectedMaxVelocity = Math.random();
        final var expectedMinVelocity = Math.random();

        final var result = Position.restore(
                expectedPosition,
                expectedRoute,
                expectedLatitude,
                expectedLongitude,
                expectedTime,
                expectedMaxVelocity,
                expectedMinVelocity,
                expectedDistance,
                expectedTime
        );

        assertEquals(expectedPosition, result.getPositionId(), MESSAGE_TO_EQUAL);
        assertEquals(expectedRoute, result.getRouteId(), MESSAGE_TO_EQUAL);
        assertEquals(expectedLatitude, result.getCoordinate().latitude(), MESSAGE_TO_EQUAL);
        assertEquals(expectedLongitude, result.getCoordinate().longitude(), MESSAGE_TO_EQUAL);
        assertEquals(expectedTime, result.getCoordinate().time(), MESSAGE_TO_EQUAL);
        assertEquals(expectedMaxVelocity, result.getMaxVelocity(), MESSAGE_TO_EQUAL);
        assertEquals(expectedMinVelocity, result.getMinVelocity(), MESSAGE_TO_EQUAL);
        assertEquals(expectedTime, result.getCreatedAt(), MESSAGE_TO_EQUAL);
    }
}
