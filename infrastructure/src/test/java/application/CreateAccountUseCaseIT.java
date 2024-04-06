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
        assertEquals(accountId, savedAccount.accountId(), MESSAGE_TO_EQUAL);
        assertEquals(expectedName, savedAccount.name(), MESSAGE_TO_EQUAL);
        assertEquals(expectedEmail, savedAccount.email(), MESSAGE_TO_EQUAL);
        assertTrue(savedAccount.active(), MESSAGE_TRUE);
        assertFalse(savedAccount.admin(), MESSAGE_TRUE);
        assertNotNull(savedAccount.createdAt(), MESSAGE_NOT_NULL);
        assertNotNull(savedAccount.lastAccess(), MESSAGE_NOT_NULL);
        assertNull(savedAccount.updatedAt(), MESSAGE_NULL);
    }
}
