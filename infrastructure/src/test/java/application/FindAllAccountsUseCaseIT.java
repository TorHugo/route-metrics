package application;

import annotation.IntegrationTest;
import com.dev.torhugo.application.dto.UcAccountDTO;
import com.dev.torhugo.application.usecase.CreateAccountUseCase;
import com.dev.torhugo.application.usecase.FindAllAccountsUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;
import static util.MessageUtils.*;

@IntegrationTest
class FindAllAccountsUseCaseIT {
    @Autowired
    private FindAllAccountsUseCase findAllAccountsUseCase;
    @Autowired
    private CreateAccountUseCase createAccountUseCase;

    @Test
    @Sql(scripts = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void shouldFindAllAccountsWithSuccess(){
        // Given
        final var expectedName = "usr account";
        final var expectedEmail = "usr@usr.com";
        final var expectedPassword = "usr12345";
        final var input = new UcAccountDTO(expectedName, expectedEmail, expectedPassword);

        // When
        final var accountId = createAccountUseCase.execute(input);
        assertNotNull(accountId, MESSAGE_NOT_NULL);
        final var savedAccount = findAllAccountsUseCase.execute();
        assertNotNull(savedAccount, MESSAGE_NOT_NULL);

        // Then
        assertEquals(accountId, savedAccount.get(0).accountId(), MESSAGE_TO_EQUAL);
        assertEquals(expectedName, savedAccount.get(0).name(), MESSAGE_TO_EQUAL);
        assertEquals(expectedEmail, savedAccount.get(0).email(), MESSAGE_TO_EQUAL);
        assertTrue(savedAccount.get(0).active(), MESSAGE_TRUE);
        assertFalse(savedAccount.get(0).admin(), MESSAGE_TRUE);
        assertNotNull(savedAccount.get(0).createdAt(), MESSAGE_NOT_NULL);
        assertNotNull(savedAccount.get(0).lastAccess(), MESSAGE_NOT_NULL);
        assertNull(savedAccount.get(0).updatedAt(), MESSAGE_NULL);
    }
}
