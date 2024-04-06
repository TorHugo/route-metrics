package application;

import annotation.IntegrationTest;
import com.dev.torhugo.application.dto.UcAccountAdminDTO;
import com.dev.torhugo.application.dto.UcAccountDTO;
import com.dev.torhugo.application.dto.UcUpdateAccountDTO;
import com.dev.torhugo.application.usecase.CreateAccountAdminUseCase;
import com.dev.torhugo.application.usecase.CreateAccountUseCase;
import com.dev.torhugo.application.usecase.FindAccountUseCase;
import com.dev.torhugo.application.usecase.UpdateAccountUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;
import static util.MessageUtils.*;

@IntegrationTest
class UpdateAccountUseCaseIT {
    @Autowired
    private UpdateAccountUseCase updateAccountUseCase;
    @Autowired
    private CreateAccountAdminUseCase createAccountAdminUseCase;
    @Autowired
    private FindAccountUseCase findAccountUseCase;

    @Test
    @Sql(scripts = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void shouldUpdateAccountWithSuccess(){
        // Given
        final var expectedName = "usr account";
        final var expectedEmail = "usr@usr.com";
        final var expectedPassword = "usr12345";
        final var newName = "usr update";
        final var newEmail = "update@usr.com";
        final var newPassword = "update12345";
        final var createAccount = new UcAccountAdminDTO(expectedName, expectedEmail, expectedPassword, true, true);

        // When
        final var accountId = createAccountAdminUseCase.execute(createAccount);
        assertNotNull(accountId, MESSAGE_NOT_NULL);
        final var previousAccount = findAccountUseCase.execute(expectedEmail);
        assertNotNull(previousAccount, MESSAGE_NOT_NULL);
        assertEquals(expectedName, previousAccount.name(), MESSAGE_TO_EQUAL);
        assertEquals(expectedEmail, previousAccount.email(), MESSAGE_TO_EQUAL);

        final var inativateAccount = new UcUpdateAccountDTO(accountId, newName, newEmail, newPassword, true, true);
        updateAccountUseCase.execute(inativateAccount);
        final var savedAccount = findAccountUseCase.execute(newEmail);
        assertNotNull(savedAccount, MESSAGE_NOT_NULL);

        // Then
        assertEquals(accountId, savedAccount.accountId(), MESSAGE_TO_EQUAL);
        assertEquals(newName, savedAccount.name(), MESSAGE_TO_EQUAL);
        assertEquals(newEmail, savedAccount.email(), MESSAGE_TO_EQUAL);
        assertTrue(savedAccount.active(), MESSAGE_TRUE);
        assertTrue(savedAccount.admin(), MESSAGE_TRUE);
        assertNotNull(savedAccount.createdAt(), MESSAGE_NOT_NULL);
        assertNotNull(savedAccount.lastAccess(), MESSAGE_NOT_NULL);
        assertNotNull(savedAccount.updatedAt(), MESSAGE_NOT_NULL);
    }
}
