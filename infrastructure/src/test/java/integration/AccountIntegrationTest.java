package integration;

import com.dev.torhugo.usecase.CreateAccountUseCase;
import com.dev.torhugo.usecase.FindAccountUseCase;
import com.dev.torhugo.models.AccountDTO;
import com.dev.torhugo.repository.AccountJpaRepository;
import config.AnnotationDefaultIT;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

class AccountIntegrationTest extends AnnotationDefaultIT {
    @Autowired
    private CreateAccountUseCase createAccountUseCase;
    @Autowired
    private FindAccountUseCase findAccountUseCase;
    @Autowired
    private AccountJpaRepository accountJpaRepository;
    @Test
    @Sql(scripts = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void shouldCreateAccountWhenValidParams(){
        // Given
        final var expectedName = "Account Test";
        final var expectedEmail = "account.test@dev.com.br";
        final var expectedPassword = "Password@";
        final var input = new AccountDTO(expectedName, expectedEmail, expectedPassword);

        // When
        final var accountId = createAccountUseCase.execute(input);
        assertNotNull(accountId, messageNotNull);
        final var actualAccount = accountJpaRepository.findById(accountId).orElse(null);
        assertNotNull(actualAccount, messageNotNull);

        // Then
        assertEquals(accountId, actualAccount.getAccountId(), messageToEqual);
        assertEquals(expectedName, actualAccount.getName(), messageToEqual);
        assertEquals(expectedEmail, actualAccount.getEmail(), messageToEqual);
        assertEquals(expectedPassword, actualAccount.getPassword(), messageToEqual);
        assertNotNull(actualAccount.getLastAccess(), messageNotNull);
        assertNotNull(actualAccount.getCreatedAt(), messageNotNull);
        assertNull(actualAccount.getUpdatedAt(), messageNull);
        assertTrue(actualAccount.isActive(), messageTrue);
        assertFalse(actualAccount.isAdmin(), messageFalse);
    }

    @Test
    @Sql(scripts = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void shouldFindAccountWithSuccess(){
        // Given
        final var expectedName = "Account Test";
        final var expectedEmail = "account.test@dev.com.br";
        final var expectedPassword = "Password@";
        final var input = new AccountDTO(expectedName, expectedEmail, expectedPassword);

        // When
        final var accountId = createAccountUseCase.execute(input);
        assertNotNull(accountId, messageNotNull);
        final var savedAccount = findAccountUseCase.execute(accountId);
        assertNotNull(savedAccount, messageNotNull);

        // Then
        assertEquals(accountId, savedAccount.accountId(), messageToEqual);
        assertEquals(expectedName, savedAccount.name(), messageToEqual);
        assertEquals(expectedEmail, savedAccount.email(), messageToEqual);
        assertNotNull(savedAccount.lastAccess(), messageNotNull);
        assertNotNull(savedAccount.createdAt(), messageNotNull);
        assertNull(savedAccount.updatedAt(), messageNull);
        assertTrue(savedAccount.active(), messageTrue);
        assertFalse(savedAccount.admin(), messageFalse);
    }
}
