package entity;

import com.dev.torhugo.domain.entity.Route;
import com.dev.torhugo.domain.exception.InvalidArgumentException;
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
        assertNull(result.getName(), MESSAGE_NULL);
        assertEquals(expectedStatus, result.getStatus(), MESSAGE_TO_EQUAL);
        assertEquals(expectedLatitude, result.getStartCoordinate().latitude(), MESSAGE_TO_EQUAL);
        assertEquals(expectedLongitude, result.getStartCoordinate().longitude(), MESSAGE_TO_EQUAL);
        assertEquals(expectedLatitude, result.getStartCoordinate().latitude(), MESSAGE_TO_EQUAL);
        assertEquals(expectedLongitude, result.getStartCoordinate().longitude(), MESSAGE_TO_EQUAL);
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
        final var expectedStatus = "finished";
        final var expectedRoute = Route.create(
                expectedAccountId,
                expectedLatitude,
                expectedLongitude
        );

        // When
        expectedRoute.confirm();
        expectedRoute.finish("");
        expectedRoute.inactive();

        // Then
        assertNotNull(expectedRoute.getRouteId(), MESSAGE_NOT_NULL);
        assertEquals(expectedAccountId, expectedRoute.getAccountId(), MESSAGE_TO_EQUAL);
        assertNotNull(expectedRoute.getName(), MESSAGE_NOT_NULL);
        assertEquals(expectedStatus, expectedRoute.getStatus(), MESSAGE_TO_EQUAL);
        assertEquals(expectedLatitude, expectedRoute.getStartCoordinate().latitude(), MESSAGE_TO_EQUAL);
        assertEquals(expectedLongitude, expectedRoute.getStartCoordinate().longitude(), MESSAGE_TO_EQUAL);
        assertEquals(expectedLatitude, expectedRoute.getStartCoordinate().latitude(), MESSAGE_TO_EQUAL);
        assertEquals(expectedLongitude, expectedRoute.getStartCoordinate().longitude(), MESSAGE_TO_EQUAL);
        assertFalse(expectedRoute.isActive(), MESSAGE_FALSE);
        assertNotNull(expectedRoute.getCreatedAt(), MESSAGE_NOT_NULL);
        assertNotNull(expectedRoute.getUpdatedAt(), MESSAGE_NULL);
    }

    @Test
    void shouldThrowExceptionWhenTheRouteIsAlreadyInactivated(){
        // Given
        final var expectedException = "This account is already inactive.";
        final var expectedAccountId = UUID.randomUUID();
        final var expectedLatitude = Math.random();
        final var expectedLongitude = Math.random();
        final var expectedRoute = Route.create(
                expectedAccountId,
                expectedLatitude,
                expectedLongitude
        );

        // When
        expectedRoute.confirm();
        expectedRoute.finish("");
        expectedRoute.inactive();
        final var result = assertThrows(InvalidArgumentException.class, expectedRoute::inactive);


        // Then
        assertNotNull(result, MESSAGE_NOT_NULL);
        assertEquals(expectedException, result.getMessage(), MESSAGE_TO_EQUAL);
    }

    @Test
    void shouldConfirmRouteWithSuccess(){
        // Given
        final var expectedAccountId = UUID.randomUUID();
        final var expectedLatitude = Math.random();
        final var expectedLongitude = Math.random();
        final var expectedStatus = "confirmed";
        final var expectedRoute = Route.create(
                expectedAccountId,
                expectedLatitude,
                expectedLongitude
        );

        // When
        expectedRoute.confirm();

        // Then
        assertNotNull(expectedRoute.getRouteId(), MESSAGE_NOT_NULL);
        assertEquals(expectedAccountId, expectedRoute.getAccountId(), MESSAGE_TO_EQUAL);
        assertNull(expectedRoute.getName(), MESSAGE_NULL);
        assertEquals(expectedStatus, expectedRoute.getStatus(), MESSAGE_TO_EQUAL);
        assertEquals(expectedLatitude, expectedRoute.getStartCoordinate().latitude(), MESSAGE_TO_EQUAL);
        assertEquals(expectedLongitude, expectedRoute.getStartCoordinate().longitude(), MESSAGE_TO_EQUAL);
        assertEquals(expectedLatitude, expectedRoute.getStartCoordinate().latitude(), MESSAGE_TO_EQUAL);
        assertEquals(expectedLongitude, expectedRoute.getStartCoordinate().longitude(), MESSAGE_TO_EQUAL);
        assertTrue(expectedRoute.isActive(), MESSAGE_TRUE);
        assertNotNull(expectedRoute.getCreatedAt(), MESSAGE_NOT_NULL);
        assertNotNull(expectedRoute.getUpdatedAt(), MESSAGE_NOT_NULL);
    }

    @Test
    void shouldThrowExceptionWhenInvalidStatus(){
        // Given
        final var expectedException = "Invalid status! Expected status: requested.";
        final var expectedAccountId = UUID.randomUUID();
        final var expectedLatitude = Math.random();
        final var expectedLongitude = Math.random();
        final var expectedRoute = Route.create(
                expectedAccountId,
                expectedLatitude,
                expectedLongitude
        );

        // When
        expectedRoute.confirm();
        final var result = assertThrows(InvalidArgumentException.class, expectedRoute::confirm);

        // Then
        assertNotNull(result, MESSAGE_NOT_NULL);
        assertEquals(expectedException, result.getMessage(), MESSAGE_TO_EQUAL);
    }

    @Test
    void shouldRestoreRouteWithSuccess(){
        // Given
        final var expectedRouteId = UUID.randomUUID();
        final var expectedAccountId = UUID.randomUUID();
        final var expectedStatus = "requested";
        final var expectedName = "test route";
        final var expectedStartLatitude = Math.random();
        final var expectedStartLongitude = Math.random();
        final var expectedStartTime = LocalDateTime.now();
        final var expectedActive = true;
        final var expectedCreatedAt = LocalDateTime.now();

        // When
        final var result = Route.restore(
                expectedRouteId,
                expectedAccountId,
                expectedStatus,
                expectedName,
                expectedStartLatitude,
                expectedStartLongitude,
                expectedStartTime,
                expectedActive,
                expectedCreatedAt,
                null
        );

        // Then
        assertEquals(expectedRouteId, result.getRouteId(), MESSAGE_TO_EQUAL);
        assertEquals(expectedAccountId, result.getAccountId(), MESSAGE_TO_EQUAL);
        assertEquals(expectedStatus, result.getStatus(), MESSAGE_TO_EQUAL);
        assertEquals(expectedName, result.getName(), MESSAGE_TO_EQUAL);
        assertEquals(expectedStartLatitude, result.getStartCoordinate().latitude(), MESSAGE_TO_EQUAL);
        assertEquals(expectedStartLongitude, result.getStartCoordinate().longitude(), MESSAGE_TO_EQUAL);
        assertEquals(expectedActive, result.isActive(), MESSAGE_TO_EQUAL);
        assertEquals(expectedCreatedAt, result.getCreatedAt(), MESSAGE_TO_EQUAL);
        assertNull(result.getUpdatedAt(), MESSAGE_NULL);
    }
}
