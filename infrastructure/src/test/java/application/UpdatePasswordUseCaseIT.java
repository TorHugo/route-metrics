package application;

import annotation.IntegrationTest;
import com.dev.torhugo.application.dto.UcAccountAdminDTO;
import com.dev.torhugo.application.dto.UcUpdatePasswordDTO;
import com.dev.torhugo.application.ports.repository.AccountRepository;
import com.dev.torhugo.application.usecase.CreateAccountAdminUseCase;
import com.dev.torhugo.application.usecase.UpdatePasswordUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;
import static util.MessageUtils.*;

@IntegrationTest
class UpdatePasswordUseCaseIT {
    @Autowired
    private UpdatePasswordUseCase updatePasswordUseCase;
    @Autowired
    private CreateAccountAdminUseCase createAccountUseCase;
    @Autowired
    private AccountRepository accountRepository;

    @Test
    @Sql(scripts = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void shouldUpdatePasswordWithSuccess(){
        // Given
        final var expectedName = "usr account";
        final var expectedEmail = "usr@usr.com";
        final var password = "usr12345";
        final var newPassword = "12345678";
        final var createAccount = new UcAccountAdminDTO(expectedName, expectedEmail, password, true, true);

        // When
        final var accountId = createAccountUseCase.execute(createAccount);
        assertNotNull(accountId, MESSAGE_NOT_NULL);

        final var savedAccount = accountRepository.findByEmailWithThrow(expectedEmail);
        assertNotNull(savedAccount, MESSAGE_NOT_NULL);

        final var input = new UcUpdatePasswordDTO(expectedEmail, newPassword);
        updatePasswordUseCase.execute(input);

        final var accountWithNewPassword = accountRepository.findByEmailWithThrow(expectedEmail);
        assertNotNull(accountWithNewPassword, MESSAGE_NOT_NULL);

        // Then
        assertEquals(accountId, accountWithNewPassword.getAccountId(), MESSAGE_TO_EQUAL);
        assertEquals(expectedName, accountWithNewPassword.getName(), MESSAGE_TO_EQUAL);
        assertEquals(expectedEmail, accountWithNewPassword.getEmail(), MESSAGE_TO_EQUAL);
        assertNotEquals(savedAccount.getPassword(), accountWithNewPassword.getPassword(), MESSAGE_NOT_EQUAL);
        assertTrue(accountWithNewPassword.isActive(), MESSAGE_TRUE);
        assertTrue(accountWithNewPassword.isAdmin(), MESSAGE_TRUE);
        assertNotNull(accountWithNewPassword.getCreatedAt(), MESSAGE_NOT_NULL);
        assertNotNull(accountWithNewPassword.getLastAccess(), MESSAGE_NOT_NULL);
        assertNotNull(accountWithNewPassword.getUpdatedAt(), MESSAGE_NOT_NULL);
    }
}
