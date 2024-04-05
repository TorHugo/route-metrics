package integration;

import com.dev.torhugo.usecase.CreateAccountUseCase;
import com.dev.torhugo.usecase.FindAccountUseCase;
import com.dev.torhugo.models.UcAccountDTO;
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
        final var input = new UcAccountDTO(expectedName, expectedEmail, expectedPassword);

        // When
        final var accountId = createAccountUseCase.execute(input);
        assertNotNull(accountId, MESSAGE_NOT_NULL);
        final var actualAccount = accountJpaRepository.findById(accountId).orElse(null);
        assertNotNull(actualAccount, MESSAGE_NOT_NULL);

        // Then
        assertEquals(accountId, actualAccount.getAccountId(), MESSAGE_TO_EQUAL);
        assertEquals(expectedName, actualAccount.getName(), MESSAGE_TO_EQUAL);
        assertEquals(expectedEmail, actualAccount.getEmail(), MESSAGE_TO_EQUAL);
        assertEquals(expectedPassword, actualAccount.getPassword(), MESSAGE_TO_EQUAL);
        assertNotNull(actualAccount.getLastAccess(), MESSAGE_NOT_NULL);
        assertNotNull(actualAccount.getCreatedAt(), MESSAGE_NOT_NULL);
        assertNull(actualAccount.getUpdatedAt(), MESSAGE_NULL);
        assertTrue(actualAccount.isActive(), MESSAGE_TRUE);
        assertFalse(actualAccount.isAdmin(), MESSAGE_FALSE);
    }

    @Test
    @Sql(scripts = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void shouldFindAccountWithSuccess(){
        // Given
        final var expectedName = "Account Test";
        final var expectedEmail = "account.test@dev.com.br";
        final var expectedPassword = "Password@";
        final var input = new UcAccountDTO(expectedName, expectedEmail, expectedPassword);

        // When
        final var accountId = createAccountUseCase.execute(input);
        assertNotNull(accountId, MESSAGE_NOT_NULL);
        final var savedAccount = findAccountUseCase.execute(accountId);
        assertNotNull(savedAccount, MESSAGE_NOT_NULL);

        // Then
        assertEquals(accountId, savedAccount.accountId(), MESSAGE_TO_EQUAL);
        assertEquals(expectedName, savedAccount.name(), MESSAGE_TO_EQUAL);
        assertEquals(expectedEmail, savedAccount.email(), MESSAGE_TO_EQUAL);
        assertNotNull(savedAccount.lastAccess(), MESSAGE_NOT_NULL);
        assertNotNull(savedAccount.createdAt(), MESSAGE_NOT_NULL);
        assertNull(savedAccount.updatedAt(), MESSAGE_NULL);
        assertTrue(savedAccount.active(), MESSAGE_TRUE);
        assertFalse(savedAccount.admin(), MESSAGE_FALSE);
    }
}
