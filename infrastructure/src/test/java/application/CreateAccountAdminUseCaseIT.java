package application;

import annotation.IntegrationTest;
import com.dev.torhugo.application.dto.UcAccountAdminDTO;
import com.dev.torhugo.application.usecase.CreateAccountAdminUseCase;
import com.dev.torhugo.application.usecase.FindAccountUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;
import static util.MessageUtils.*;

@IntegrationTest
class CreateAccountAdminUseCaseIT {
    @Autowired
    private CreateAccountAdminUseCase createAccountAdminUseCase;
    @Autowired
    private FindAccountUseCase findAccountUseCase;

    @Test
    @Sql(scripts = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void shouldCreateAccountAdminWhenIsValidParameters(){
        // Given
        final var expectedName = "admin account";
        final var expectedEmail = "admin@admin.com";
        final var expectedPassword = "admin12345";
        final var input = new UcAccountAdminDTO(expectedName, expectedEmail, expectedPassword, true, true);

        // When
        final var accountId = createAccountAdminUseCase.execute(input);
        assertNotNull(accountId, MESSAGE_NOT_NULL);
        final var savedAccount = findAccountUseCase.execute(expectedEmail);
        assertNotNull(savedAccount, MESSAGE_NOT_NULL);

        // Then
        assertEquals(accountId, savedAccount.getAccountId(), MESSAGE_TO_EQUAL);
        assertEquals(expectedName, savedAccount.getName(), MESSAGE_TO_EQUAL);
        assertEquals(expectedEmail, savedAccount.getEmail(), MESSAGE_TO_EQUAL);
        assertTrue(savedAccount.isActive(), MESSAGE_TRUE);
        assertTrue(savedAccount.isAdmin(), MESSAGE_TRUE);
        assertNotNull(savedAccount.getCreatedAt(), MESSAGE_NOT_NULL);
        assertNotNull(savedAccount.getLastAccess(), MESSAGE_NOT_NULL);
        assertNull(savedAccount.getUpdatedAt(), MESSAGE_NULL);
    }
}
