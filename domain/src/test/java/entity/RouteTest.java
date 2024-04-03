package entity;

import com.dev.torhugo.domain.entity.Route;
import util.MessageUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class RouteTest extends MessageUtil {
    @Test
    void shouldInstantiateRouteWhenValidParameters(){
        // Given
        final var expectedAccountId = UUID.randomUUID();
        final var expectedLatitude = Math.random();
        final var expectedLongitude = Math.random();
        final var expectedStatus = "REQUESTED";

        // When
        final var result = Route.create(
                expectedAccountId,
                expectedLatitude,
                expectedLongitude
        );

        // Then
        assertNotNull(result.getRouteId(), MESSAGE_NOT_NULL);
        assertEquals(expectedAccountId, result.getAccountId(), MESSAGE_TO_EQUAL);
        assertNull(result.getDistance(), MESSAGE_NULL);
        assertEquals(expectedStatus, result.getStatus(), MESSAGE_TO_EQUAL);
        assertEquals(expectedLatitude, result.getInitialCoord().latitude(), MESSAGE_TO_EQUAL);
        assertEquals(expectedLongitude, result.getInitialCoord().longitude(), MESSAGE_TO_EQUAL);
        assertEquals(expectedLatitude, result.getLastCoord().latitude(), MESSAGE_TO_EQUAL);
        assertEquals(expectedLongitude, result.getLastCoord().longitude(), MESSAGE_TO_EQUAL);
        assertTrue(result.getActive(), MESSAGE_TRUE);
        assertNotNull(result.getCreatedAt(), MESSAGE_NOT_NULL);
        assertNull(result.getUpdatedAt(), MESSAGE_NULL);
    }

    @Test
    void shouldInactiveAccountWithSuccess(){
        // Given
        final var expectedAccountId = UUID.randomUUID();
        final var expectedLatitude = Math.random();
        final var expectedLongitude = Math.random();
        final var expectedStatus = "REQUESTED";
        final var expectedRoute = Route.create(
                expectedAccountId,
                expectedLatitude,
                expectedLongitude
        );

        // When
        final var result = expectedRoute.inactive(
                expectedRoute.getRouteId(),
                expectedRoute.getAccountId(),
                expectedRoute.getDistance(),
                expectedRoute.getStatus(),
                expectedRoute.getInitialCoord().latitude(),
                expectedRoute.getInitialCoord().longitude(),
                expectedRoute.getLastCoord().latitude(),
                expectedRoute.getLastCoord().longitude(),
                expectedRoute.getCreatedAt()
        );

        // Then
        assertNotNull(result.getRouteId(), MESSAGE_NOT_NULL);
        assertEquals(expectedAccountId, result.getAccountId(), MESSAGE_TO_EQUAL);
        assertNull(result.getDistance(), MESSAGE_NULL);
        assertEquals(expectedStatus, result.getStatus(), MESSAGE_TO_EQUAL);
        assertEquals(expectedLatitude, result.getInitialCoord().latitude(), MESSAGE_TO_EQUAL);
        assertEquals(expectedLongitude, result.getInitialCoord().longitude(), MESSAGE_TO_EQUAL);
        assertEquals(expectedLatitude, result.getLastCoord().latitude(), MESSAGE_TO_EQUAL);
        assertEquals(expectedLongitude, result.getLastCoord().longitude(), MESSAGE_TO_EQUAL);
        assertFalse(result.getActive(), MESSAGE_FALSE);
        assertNotNull(result.getCreatedAt(), MESSAGE_NOT_NULL);
        assertNotNull(result.getUpdatedAt(), MESSAGE_NULL);
    }

    @Test
    void shouldRestoreRouteWithSuccess(){
        // Given
        final var expectedRouteId = UUID.randomUUID();
        final var expectedAccountId = UUID.randomUUID();
        final var expectedDistance = Math.random();
        final var expectedStatus = "REQUESTED";
        final var expectedInitialLat = Math.random();
        final var expectedInitialLong = Math.random();
        final var expectedLastLat = Math.random();
        final var expectedLastLong = Math.random();
        final var expectedActive = true;
        final var expectedCreatedAt = LocalDateTime.now();

        // When
        final var result = Route.restore(
                expectedRouteId,
                expectedAccountId,
                expectedDistance,
                expectedStatus,
                expectedInitialLat,
                expectedInitialLong,
                expectedLastLat,
                expectedLastLong,
                expectedActive,
                expectedCreatedAt,
                null
        );

        // Then
        assertEquals(expectedRouteId, result.getRouteId(), MESSAGE_TO_EQUAL);
        assertEquals(expectedAccountId, result.getAccountId(), MESSAGE_TO_EQUAL);
        assertEquals(expectedDistance, result.getDistance(), MESSAGE_TO_EQUAL);
        assertEquals(expectedStatus, result.getStatus(), MESSAGE_TO_EQUAL);
        assertEquals(expectedInitialLat, result.getInitialCoord().latitude(), MESSAGE_TO_EQUAL);
        assertEquals(expectedInitialLong, result.getInitialCoord().longitude(), MESSAGE_TO_EQUAL);
        assertEquals(expectedLastLat, result.getLastCoord().latitude(), MESSAGE_TO_EQUAL);
        assertEquals(expectedLastLong, result.getLastCoord().longitude(), MESSAGE_TO_EQUAL);
        assertEquals(expectedActive, result.getActive(), MESSAGE_TO_EQUAL);
        assertEquals(expectedCreatedAt, result.getCreatedAt(), MESSAGE_TO_EQUAL);
        assertNull(result.getUpdatedAt(), MESSAGE_NULL);
    }
}
