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
        final var expectedStatus = "requested";

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
        assertNull(result.getName(), MESSAGE_NULL);
        assertEquals(expectedStatus, result.getStatus(), MESSAGE_TO_EQUAL);
        assertEquals(expectedLatitude, result.getInitialCoord().latitude(), MESSAGE_TO_EQUAL);
        assertEquals(expectedLongitude, result.getInitialCoord().longitude(), MESSAGE_TO_EQUAL);
        assertEquals(expectedLatitude, result.getLastCoord().latitude(), MESSAGE_TO_EQUAL);
        assertEquals(expectedLongitude, result.getLastCoord().longitude(), MESSAGE_TO_EQUAL);
        assertTrue(result.isActive(), MESSAGE_TRUE);
        assertNotNull(result.getCreatedAt(), MESSAGE_NOT_NULL);
        assertNull(result.getUpdatedAt(), MESSAGE_NULL);
    }

    @Test
    void shouldInactiveRouteWithSuccess(){
        // Given
        final var expectedAccountId = UUID.randomUUID();
        final var expectedLatitude = Math.random();
        final var expectedLongitude = Math.random();
        final var expectedStatus = "requested";
        final var expectedRoute = Route.create(
                expectedAccountId,
                expectedLatitude,
                expectedLongitude
        );

        // When
        expectedRoute.inactive();

        // Then
        assertNotNull(expectedRoute.getRouteId(), MESSAGE_NOT_NULL);
        assertEquals(expectedAccountId, expectedRoute.getAccountId(), MESSAGE_TO_EQUAL);
        assertNull(expectedRoute.getDistance(), MESSAGE_NULL);
        assertNull(expectedRoute.getName(), MESSAGE_NULL);
        assertEquals(expectedStatus, expectedRoute.getStatus(), MESSAGE_TO_EQUAL);
        assertEquals(expectedLatitude, expectedRoute.getInitialCoord().latitude(), MESSAGE_TO_EQUAL);
        assertEquals(expectedLongitude, expectedRoute.getInitialCoord().longitude(), MESSAGE_TO_EQUAL);
        assertEquals(expectedLatitude, expectedRoute.getLastCoord().latitude(), MESSAGE_TO_EQUAL);
        assertEquals(expectedLongitude, expectedRoute.getLastCoord().longitude(), MESSAGE_TO_EQUAL);
        assertFalse(expectedRoute.isActive(), MESSAGE_FALSE);
        assertNotNull(expectedRoute.getCreatedAt(), MESSAGE_NOT_NULL);
        assertNotNull(expectedRoute.getUpdatedAt(), MESSAGE_NULL);
    }

    @Test
    void shouldRestoreRouteWithSuccess(){
        // Given
        final var expectedRouteId = UUID.randomUUID();
        final var expectedAccountId = UUID.randomUUID();
        final var expectedDistance = Math.random();
        final var expectedStatus = "requested";
        final var expectedName = "test route";
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
                expectedName,
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
        assertEquals(expectedName, result.getName(), MESSAGE_TO_EQUAL);
        assertEquals(expectedInitialLat, result.getInitialCoord().latitude(), MESSAGE_TO_EQUAL);
        assertEquals(expectedInitialLong, result.getInitialCoord().longitude(), MESSAGE_TO_EQUAL);
        assertEquals(expectedLastLat, result.getLastCoord().latitude(), MESSAGE_TO_EQUAL);
        assertEquals(expectedLastLong, result.getLastCoord().longitude(), MESSAGE_TO_EQUAL);
        assertEquals(expectedActive, result.isActive(), MESSAGE_TO_EQUAL);
        assertEquals(expectedCreatedAt, result.getCreatedAt(), MESSAGE_TO_EQUAL);
        assertNull(result.getUpdatedAt(), MESSAGE_NULL);
    }
}
