package application;

import annotation.IntegrationTest;
import com.dev.torhugo.application.dto.UcAccountDTO;
import com.dev.torhugo.application.usecase.CreateAccountUseCase;
import com.dev.torhugo.application.usecase.FindAccountUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;
import static util.MessageUtils.*;

@IntegrationTest
class CreateAccountUseCaseIT {
    @Autowired
    private CreateAccountUseCase createAccountUseCase;
    @Autowired
    private FindAccountUseCase findAccountUseCase;

    @Test
    @Sql(scripts = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void shouldCreateAccountWhenIsValidParameters(){
        // Given
        final var expectedName = "usr account";
        final var expectedEmail = "usr@usr.com";
        final var expectedPassword = "usr12345";
        final var input = new UcAccountDTO(expectedName, expectedEmail, expectedPassword);

        // When
        final var accountId = createAccountUseCase.execute(input);
        assertNotNull(accountId, MESSAGE_NOT_NULL);
        final var savedAccount = findAccountUseCase.execute(expectedEmail);
        assertNotNull(savedAccount, MESSAGE_NOT_NULL);

        // Then
        assertEquals(accountId, savedAccount.getAccountId(), MESSAGE_TO_EQUAL);
        assertEquals(expectedName, savedAccount.getName(), MESSAGE_TO_EQUAL);
        assertEquals(expectedEmail, savedAccount.getEmail(), MESSAGE_TO_EQUAL);
        assertTrue(savedAccount.isActive(), MESSAGE_TRUE);
        assertFalse(savedAccount.isAdmin(), MESSAGE_TRUE);
        assertNotNull(savedAccount.getCreatedAt(), MESSAGE_NOT_NULL);
        assertNotNull(savedAccount.getLastAccess(), MESSAGE_NOT_NULL);
        assertNull(savedAccount.getUpdatedAt(), MESSAGE_NULL);
    }
}
