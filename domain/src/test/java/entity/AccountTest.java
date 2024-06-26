package entity;

import com.dev.torhugo.domain.entity.Account;
import com.dev.torhugo.domain.exception.InvalidArgumentException;
import org.junit.jupiter.api.Test;
import util.MessageUtil;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest extends MessageUtil {
    @Test
    void shouldCreateAccountWhenIsValidParameters(){
        // Given
        final var expectedName = "Account Test";
        final var expectedEmail = "account.test@dev.com.br";
        final var expectedPassword = "Password@";

        // When
        final var result = Account.create(
                expectedName,
                expectedEmail,
                expectedPassword
        );

        // Then
        assertNotNull(result.getAccountId(), MESSAGE_NOT_NULL);
        assertEquals(expectedName, result.getName(), MESSAGE_TO_EQUAL);
        assertEquals(expectedEmail, result.getEmail(), MESSAGE_TO_EQUAL);
        assertEquals(expectedPassword, result.getPassword(), MESSAGE_TO_EQUAL);
        assertTrue(result.isActive(), MESSAGE_TRUE);
        assertFalse(result.isAdmin(), MESSAGE_FALSE);
        assertNotNull(result.getLastAccess(), MESSAGE_NOT_NULL);
        assertNotNull(result.getCreatedAt(), MESSAGE_NOT_NULL);
        assertNull(result.getUpdatedAt(), MESSAGE_NULL);
    }
    @Test
    void shouldCreateAdminAccountWhenIsValidParameters(){
        // Given
        final var expectedName = "Account Test";
        final var expectedEmail = "account.test@dev.com.br";
        final var expectedPassword = "Password@";
        final var expectedActive = false;
        final var expectedAdmin = true;

        // When
        final var result = Account.create(
                expectedName,
                expectedEmail,
                expectedPassword,
                expectedActive,
                expectedAdmin
        );

        // Then
        assertNotNull(result.getAccountId(), MESSAGE_NOT_NULL);
        assertEquals(expectedName, result.getName(), MESSAGE_TO_EQUAL);
        assertEquals(expectedEmail, result.getEmail(), MESSAGE_TO_EQUAL);
        assertEquals(expectedPassword, result.getPassword(), MESSAGE_TO_EQUAL);
        assertEquals(expectedActive, result.isActive(), MESSAGE_TO_EQUAL);
        assertEquals(expectedAdmin, result.isAdmin(), MESSAGE_TO_EQUAL);
        assertNotNull(result.getLastAccess(), MESSAGE_NOT_NULL);
        assertNotNull(result.getCreatedAt(), MESSAGE_NOT_NULL);
        assertNull(result.getUpdatedAt(), MESSAGE_NULL);
    }

    @Test
    void shouldInactiveAccountWithSuccess(){
        // Given
        final var expectedAccountId = UUID.randomUUID();
        final var expectedName = "Account Test";
        final var expectedEmail = "account.test@dev.com.br";
        final var expectedPassword = "Password@";
        final var expectedAccount = Account.create(
                expectedName,
                expectedEmail,
                expectedPassword
        );

        // When
        expectedAccount.inactive();

        // Then
        assertNotEquals(expectedAccountId, expectedAccount.getAccountId(), MESSAGE_NOT_EQUAL);
        assertEquals(expectedName, expectedAccount.getName(), MESSAGE_TO_EQUAL);
        assertEquals(expectedEmail, expectedAccount.getEmail(), MESSAGE_TO_EQUAL);
        assertEquals(expectedPassword, expectedAccount.getPassword(), MESSAGE_TO_EQUAL);
        assertFalse(expectedAccount.isActive(), MESSAGE_TRUE);
        assertFalse(expectedAccount.isAdmin(), MESSAGE_FALSE);
        assertNotNull(expectedAccount.getLastAccess(), MESSAGE_NOT_NULL);
        assertNotNull(expectedAccount.getCreatedAt(), MESSAGE_NOT_NULL);
        assertNotNull(expectedAccount.getUpdatedAt(), MESSAGE_NOT_NULL);
    }

    @Test
    void shouldRestoreAccountWithSuccess(){
        // Given
        final var expectedAccountId = UUID.randomUUID();
        final var expectedName = "Account Test";
        final var expectedEmail = "account.test@dev.com.br";
        final var expectedPassword = "Password@";
        final var expectedActive = true;
        final var expectedAdmin = false;
        final var expectedLastAccess = LocalDateTime.now();
        final var expectedCreatedAt = LocalDateTime.now();

        // When
        final var result = Account.restore(
                expectedAccountId,
                expectedName,
                expectedEmail,
                expectedPassword,
                expectedActive,
                expectedAdmin,
                expectedLastAccess,
                expectedCreatedAt,
                null
        );

        // Then
        assertEquals(expectedAccountId, result.getAccountId(), MESSAGE_TO_EQUAL);
        assertEquals(expectedName, result.getName(), MESSAGE_TO_EQUAL);
        assertEquals(expectedEmail, result.getEmail(), MESSAGE_TO_EQUAL);
        assertEquals(expectedPassword, result.getPassword(), MESSAGE_TO_EQUAL);
        assertEquals(expectedActive, result.isActive(), MESSAGE_TO_EQUAL);
        assertEquals(expectedAdmin, result.isAdmin(), MESSAGE_TO_EQUAL);
        assertEquals(expectedLastAccess, result.getLastAccess(), MESSAGE_TO_EQUAL);
        assertEquals(expectedCreatedAt, result.getCreatedAt(), MESSAGE_TO_EQUAL);
        assertNull(result.getUpdatedAt(), MESSAGE_NULL);
    }

    @Test
    void shouldUpdateAccountWithSuccess(){
        // Given
        final var expectedAccountId = UUID.randomUUID();
        final var expectedName = "Account Test";
        final var expectedEmail = "account.test@dev.com.br";
        final var expectedPassword = "Password@";
        final var expectedActive = true;
        final var expectedAdmin = false;
        final var expectedLastAccess = LocalDateTime.now();
        final var expectedCreatedAt = LocalDateTime.now();

        // When
        final var result = Account.update(
                expectedAccountId,
                expectedName,
                expectedEmail,
                expectedPassword,
                expectedActive,
                expectedAdmin,
                expectedLastAccess,
                expectedCreatedAt
        );

        // Then
        assertEquals(expectedAccountId, result.getAccountId(), MESSAGE_TO_EQUAL);
        assertEquals(expectedName, result.getName(), MESSAGE_TO_EQUAL);
        assertEquals(expectedEmail, result.getEmail(), MESSAGE_TO_EQUAL);
        assertEquals(expectedPassword, result.getPassword(), MESSAGE_TO_EQUAL);
        assertEquals(expectedActive, result.isActive(), MESSAGE_TO_EQUAL);
        assertEquals(expectedAdmin, result.isAdmin(), MESSAGE_TO_EQUAL);
        assertEquals(expectedLastAccess, result.getLastAccess(), MESSAGE_TO_EQUAL);
        assertEquals(expectedCreatedAt, result.getCreatedAt(), MESSAGE_TO_EQUAL);
        assertNotNull(result.getUpdatedAt(), MESSAGE_NOT_NULL);
    }

    @Test
    void shouldThrowExceptionWhenInvalidEmail(){
        // Given
        final var expectedName = "Account Test";
        final var expectedEmail = "account.test_dev.com.br";
        final var expectedPassword = "Password@";
        final var expectedErrorMessage = "Invalid email!";

        // When
        final var exception = assertThrows(InvalidArgumentException.class, () ->
            Account.create(
                expectedName,
                expectedEmail,
                expectedPassword
            ));

        // Then
        assertEquals(expectedErrorMessage, exception.getMessage(), MESSAGE_TO_EQUAL);
    }

    @Test
    void shouldThrowExceptionWhenInvalidPassword(){
        // Given
        final var expectedName = "Account Test";
        final var expectedEmail = "account.test@dev.com.br";
        final var expectedPassword = "abc";
        final var expectedErrorMessage = "Invalid password!";

        // When
        final var exception = assertThrows(InvalidArgumentException.class, () ->
                Account.create(
                expectedName,
                expectedEmail,
                expectedPassword
        ));

        // Then
        assertEquals(expectedErrorMessage, exception.getMessage(), MESSAGE_TO_EQUAL);
    }

    @Test
    void shouldThrowExceptionWhenInactiveAccount() {
        // Given
        final var expectedErrorMessage = "This account is already inactive.";
        final var expectedName = "Account Test";
        final var expectedEmail = "account.test@dev.com.br";
        final var expectedPassword = "Password@";
        final var expectedAccount = Account.create(
                expectedName,
                expectedEmail,
                expectedPassword
        );
        expectedAccount.inactive();

        // When
        final var exception = assertThrows(InvalidArgumentException.class, expectedAccount::inactive);

        // Then
        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    void shouldUpdatePasswordWithSuccess(){
        final var expectedName = "Account Test";
        final var expectedEmail = "account.test@dev.com.br";
        final var expectedPassword = "Password@";
        final var newPassword = "newPassword";
        final var expectedAccount = Account.create(
                expectedName,
                expectedEmail,
                expectedPassword
        );

        expectedAccount.updatePassword(newPassword);


        assertNotNull(expectedAccount.getAccountId(), MESSAGE_NOT_NULL);
        assertEquals(expectedName, expectedAccount.getName(), MESSAGE_TO_EQUAL);
        assertEquals(expectedEmail, expectedAccount.getEmail(), MESSAGE_TO_EQUAL);
        assertEquals(newPassword, expectedAccount.getPassword(), MESSAGE_TO_EQUAL);
        assertTrue(expectedAccount.isActive(), MESSAGE_TRUE);
        assertFalse(expectedAccount.isAdmin(), MESSAGE_FALSE);
        assertNotNull(expectedAccount.getLastAccess(), MESSAGE_NOT_NULL);
        assertNotNull(expectedAccount.getCreatedAt(), MESSAGE_NOT_NULL);
        assertNotNull(expectedAccount.getUpdatedAt(), MESSAGE_NOT_NULL);
    }

    @Test
    void shoulUpdateLastAccessWithSuccess(){
        final var expectedName = "Account Test";
        final var expectedEmail = "account.test@dev.com.br";
        final var expectedPassword = "Password@";
        final var expectedAccount = Account.create(
                expectedName,
                expectedEmail,
                expectedPassword
        );

        expectedAccount.lastAccess();

        assertNotNull(expectedAccount.getAccountId(), MESSAGE_NOT_NULL);
        assertEquals(expectedName, expectedAccount.getName(), MESSAGE_TO_EQUAL);
        assertEquals(expectedEmail, expectedAccount.getEmail(), MESSAGE_TO_EQUAL);
        assertEquals(expectedPassword, expectedAccount.getPassword(), MESSAGE_TO_EQUAL);
        assertTrue(expectedAccount.isActive(), MESSAGE_TRUE);
        assertFalse(expectedAccount.isAdmin(), MESSAGE_FALSE);
        assertNotNull(expectedAccount.getLastAccess(), MESSAGE_NOT_NULL);
        assertNotNull(expectedAccount.getCreatedAt(), MESSAGE_NOT_NULL);
        assertNotNull(expectedAccount.getUpdatedAt(), MESSAGE_NOT_NULL);
    }
}
