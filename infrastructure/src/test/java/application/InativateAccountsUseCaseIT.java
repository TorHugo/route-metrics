package application;

import annotation.IntegrationTest;
import com.dev.torhugo.application.dto.UcAccountDTO;
import com.dev.torhugo.application.dto.UcInativateAccountDTO;
import com.dev.torhugo.application.usecase.CreateAccountUseCase;
import com.dev.torhugo.application.usecase.FindAccountUseCase;
import com.dev.torhugo.application.usecase.InativateAccountUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static util.MessageUtils.*;

@IntegrationTest
class InativateAccountsUseCaseIT {
    @Autowired
    private InativateAccountUseCase inativateAccountUseCase;
    @Autowired
    private CreateAccountUseCase createAccountUseCase;
    @Autowired
    private FindAccountUseCase findAccountUseCase;

    @Test
    @Sql(scripts = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void shouldInativateAccountWithSuccess(){
        // Given
        final var expectedName = "usr account";
        final var expectedEmail = "usr@usr.com";
        final var expectedPassword = "usr12345";
        final var createAccount = new UcAccountDTO(expectedName, expectedEmail, expectedPassword);

        // When
        final var accountId = createAccountUseCase.execute(createAccount);
        assertNotNull(accountId, MESSAGE_NOT_NULL);
        final var inativateAccount = new UcInativateAccountDTO(List.of(accountId));
        inativateAccountUseCase.execute(inativateAccount);
        final var savedAccount = findAccountUseCase.execute(expectedEmail);
        assertNotNull(savedAccount, MESSAGE_NOT_NULL);

        // Then
        assertEquals(accountId, savedAccount.accountId(), MESSAGE_TO_EQUAL);
        assertEquals(expectedName, savedAccount.name(), MESSAGE_TO_EQUAL);
        assertEquals(expectedEmail, savedAccount.email(), MESSAGE_TO_EQUAL);
        assertFalse(savedAccount.active(), MESSAGE_TRUE);
        assertFalse(savedAccount.admin(), MESSAGE_TRUE);
        assertNotNull(savedAccount.createdAt(), MESSAGE_NOT_NULL);
        assertNotNull(savedAccount.lastAccess(), MESSAGE_NOT_NULL);
        assertNotNull(savedAccount.updatedAt(), MESSAGE_NOT_NULL);
    }
}
