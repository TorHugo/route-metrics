package entity;

import com.dev.torhugo.domain.entity.ForgetPassword;
import org.junit.jupiter.api.Test;
import util.MessageUtil;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ForgetPasswordTest extends MessageUtil {
    @Test
    void shouldCreateForgetPasswordWhenIsValidParameters(){
        // Given
        final var expectedAccountId = UUID.randomUUID();

        // When
        final var result = ForgetPassword.create(expectedAccountId);

        // Then
        assertNotNull(result, MESSAGE_NOT_NULL);
        assertNotNull(result.getForgetPasswordId(), MESSAGE_NOT_NULL);
        assertEquals(expectedAccountId, result.getAccountId(), MESSAGE_TO_EQUAL);
        assertNotNull(result.getHashCode(), MESSAGE_NOT_NULL);
        assertNotNull(result.getExpirationDate(), MESSAGE_NOT_NULL);
        assertTrue(result.isActive(), MESSAGE_TRUE);
        assertFalse(result.isConfirmed(), MESSAGE_FALSE);
        assertNotNull(result.getCreatedAt(), MESSAGE_NOT_NULL);
        assertNull(result.getUpdatedAt(), MESSAGE_NULL);
    }

    @Test
    void shouldRestoreForgetPasswordWithSuccess(){
        // Given
        final var expectedForgetPasswordId = UUID.randomUUID();
        final var expectedAccountId = UUID.randomUUID();
        final var expectedHashCode = "123456";
        final var expectedExpirationDate = LocalDateTime.now().plusMinutes(30L);
        final var expectedActive = true;
        final var expectedConfirmed = false;
        final var expectedCreatedAt = LocalDateTime.now();

        // When
        final var result = ForgetPassword.restore(
                expectedForgetPasswordId,
                expectedAccountId,
                expectedHashCode,
                expectedExpirationDate,
                expectedActive,
                expectedConfirmed,
                expectedCreatedAt,
                null
        );

        // Then
        assertNotNull(result, MESSAGE_NOT_NULL);
        assertEquals(expectedForgetPasswordId, result.getForgetPasswordId(), MESSAGE_TO_EQUAL);
        assertEquals(expectedAccountId, result.getAccountId(), MESSAGE_TO_EQUAL);
        assertEquals(expectedHashCode, result.getHashCode(), MESSAGE_TO_EQUAL);
        assertEquals(expectedExpirationDate, result.getExpirationDate(), MESSAGE_TO_EQUAL);
        assertEquals(expectedActive, result.isActive(), MESSAGE_TO_EQUAL);
        assertEquals(expectedConfirmed, result.isConfirmed(), MESSAGE_TO_EQUAL);
        assertEquals(expectedCreatedAt, result.getCreatedAt(), MESSAGE_TO_EQUAL);
        assertNull(result.getUpdatedAt(), MESSAGE_NULL);
    }

    @Test
    void shouldConfirmedForgetPasswordWithSuccess(){
        // Given
        final var expectedForgetPasswordId = UUID.randomUUID();
        final var expectedAccountId = UUID.randomUUID();
        final var expectedHashCode = "123456";
        final var expectedExpirationDate = LocalDateTime.now().plusMinutes(30L);
        final var expectedActive = true;
        final var expectedCreatedAt = LocalDateTime.now();

        // When
        final var result = ForgetPassword.confirmed(
                expectedForgetPasswordId,
                expectedAccountId,
                expectedHashCode,
                expectedActive,
                expectedExpirationDate,
                expectedCreatedAt
        );

        // Then
        assertNotNull(result, MESSAGE_NOT_NULL);
        assertEquals(expectedForgetPasswordId, result.getForgetPasswordId(), MESSAGE_TO_EQUAL);
        assertEquals(expectedAccountId, result.getAccountId(), MESSAGE_TO_EQUAL);
        assertEquals(expectedHashCode, result.getHashCode(), MESSAGE_TO_EQUAL);
        assertEquals(expectedExpirationDate, result.getExpirationDate(), MESSAGE_TO_EQUAL);
        assertTrue(result.isActive(), MESSAGE_TRUE);
        assertTrue(result.isConfirmed(), MESSAGE_TRUE);
        assertEquals(expectedCreatedAt, result.getCreatedAt(), MESSAGE_TO_EQUAL);
        assertNotNull(result.getUpdatedAt(), MESSAGE_NOT_NULL);
    }

    @Test
    void shouldInactivedForgetPasswordWithSuccess(){
        // Given
        final var expectedForgetPasswordId = UUID.randomUUID();
        final var expectedAccountId = UUID.randomUUID();
        final var expectedHashCode = "123456";
        final var expectedExpirationDate = LocalDateTime.now().plusMinutes(30L);
        final var expectedConfirmed = true;
        final var expectedCreatedAt = LocalDateTime.now();

        // When
        final var result = ForgetPassword.inactived(
                expectedForgetPasswordId,
                expectedAccountId,
                expectedHashCode,
                expectedConfirmed,
                expectedExpirationDate,
                expectedCreatedAt
        );

        // Then
        assertNotNull(result, MESSAGE_NOT_NULL);
        assertEquals(expectedForgetPasswordId, result.getForgetPasswordId(), MESSAGE_TO_EQUAL);
        assertEquals(expectedAccountId, result.getAccountId(), MESSAGE_TO_EQUAL);
        assertEquals(expectedHashCode, result.getHashCode(), MESSAGE_TO_EQUAL);
        assertEquals(expectedExpirationDate, result.getExpirationDate(), MESSAGE_TO_EQUAL);
        assertFalse(result.isActive(), MESSAGE_FALSE);
        assertTrue(result.isConfirmed(), MESSAGE_TRUE);
        assertEquals(expectedCreatedAt, result.getCreatedAt(), MESSAGE_TO_EQUAL);
        assertNotNull(result.getUpdatedAt(), MESSAGE_NOT_NULL);
    }
}
