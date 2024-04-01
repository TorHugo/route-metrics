import com.dev.torhugo.domain.entity.Account;
import com.dev.torhugo.domain.error.exception.InvalidArgumentError;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest extends MessageTest {
    @Test
    void shouldInstantiateAccountWhenValidParameters(){
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
        assertNotNull(result.getAccountId(), messageNotNull);
        assertEquals(expectedName, result.getName(), messageToEqual);
        assertEquals(expectedEmail, result.getEmail(), messageToEqual);
        assertEquals(expectedPassword, result.getPassword(), messageToEqual);
        assertTrue(result.isActive(), messageTrue);
        assertFalse(result.isAdmin(), messageFalse);
        assertNotNull(result.getLastAccess(), messageNotNull);
        assertNotNull(result.getCreatedAt(), messageNotNull);
        assertNull(result.getUpdatedAt(), messageNull);
    }

    @Test
    void shouldInactiveAccountWithSuccess(){
        // Given
        final var expectedAccountId = UUID.randomUUID();
        final var expectedName = "Account Test";
        final var expectedEmail = "account.test@dev.com.br";
        final var expectedPassword = "Password@";
        final var expectedAdmin = false;
        final var expectedCreatedAt = LocalDateTime.now();
        final var expectedAccount = Account.create(
                expectedName,
                expectedEmail,
                expectedPassword
        );

        // When
        final var result = expectedAccount.inactive(
                expectedAccountId,
                expectedName,
                expectedEmail,
                expectedPassword,
                expectedAdmin,
                expectedCreatedAt
        );

        // Then
        assertEquals(expectedAccountId, result.getAccountId(), messageToEqual);
        assertEquals(expectedName, result.getName(), messageToEqual);
        assertEquals(expectedEmail, result.getEmail(), messageToEqual);
        assertEquals(expectedPassword, result.getPassword(), messageToEqual);
        assertFalse(result.isActive(), messageTrue);
        assertFalse(result.isAdmin(), messageFalse);
        assertNotNull(result.getLastAccess(), messageNotNull);
        assertNotNull(result.getCreatedAt(), messageNotNull);
        assertNotNull(result.getUpdatedAt(), messageNotNull);
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
                expectedCreatedAt,
                null,
                expectedLastAccess
        );

        // Then
        assertEquals(expectedAccountId, result.getAccountId(), messageToEqual);
        assertEquals(expectedName, result.getName(), messageToEqual);
        assertEquals(expectedEmail, result.getEmail(), messageToEqual);
        assertEquals(expectedPassword, result.getPassword(), messageToEqual);
        assertEquals(expectedActive, result.isActive(), messageToEqual);
        assertEquals(expectedAdmin, result.isAdmin(), messageToEqual);
        assertEquals(expectedLastAccess, result.getLastAccess(), messageToEqual);
        assertEquals(expectedCreatedAt, result.getCreatedAt(), messageNotNull);
        assertNull(result.getUpdatedAt(), messageNull);
    }

    @Test
    void shouldThrowExceptionWhenInvalidEmail(){
        // Given
        final var expectedName = "Account Test";
        final var expectedEmail = "account.test_dev.com.br";
        final var expectedPassword = "Password@";
        final var expectedErrorMessage = "Invalid email!";

        // When
        final var exception = assertThrows(InvalidArgumentError.class, () -> {
            Account.create(
                    expectedName,
                    expectedEmail,
                    expectedPassword
            );
        });

        // Then
        assertEquals(expectedErrorMessage, exception.getMessage(), messageToEqual);
    }

    @Test
    void shouldThrowExceptionWhenInvalidPassword(){
        // Given
        final var expectedName = "Account Test";
        final var expectedEmail = "account.test@dev.com.br";
        final var expectedPassword = "abc";
        final var expectedErrorMessage = "Invalid password!";

        // When
        final var exception = assertThrows(InvalidArgumentError.class, () ->
                Account.create(
                expectedName,
                expectedEmail,
                expectedPassword
        ));

        // Then
        assertEquals(expectedErrorMessage, exception.getMessage(), messageToEqual);
    }

    @Test
    void shouldThrowExceptionWhenInactiveAccount() {
        // Given
        final var expectedErrorMessage = "This account is already inactive.";
        final var expectedAccountId = UUID.randomUUID();
        final var expectedName = "Account Test";
        final var expectedEmail = "account.test@dev.com.br";
        final var expectedPassword = "Password@";
        final var expectedAdmin = false;
        final var expectedCreatedAt = LocalDateTime.now();
        final var expectedAccount = Account.create(
                expectedName,
                expectedEmail,
                expectedPassword
        );
        final var expectedInactiveAccount = expectedAccount.inactive(
                expectedAccountId,
                expectedName,
                expectedEmail,
                expectedPassword,
                expectedAdmin,
                expectedCreatedAt
        );

        // When
        final var exception = assertThrows(InvalidArgumentError.class, () ->
                expectedInactiveAccount.inactive(
                expectedAccountId,
                expectedName,
                expectedEmail,
                expectedPassword,
                expectedAdmin,
                expectedCreatedAt
        ));

        // Then
        assertEquals(expectedErrorMessage, exception.getMessage());
    }
}
