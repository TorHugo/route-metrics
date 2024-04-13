package application;

import annotation.IntegrationTest;
import com.dev.torhugo.application.dto.UcSendPasswordDTO;
import com.dev.torhugo.application.ports.repository.AccountRepository;
import com.dev.torhugo.application.ports.repository.ForgetPasswordRepository;
import com.dev.torhugo.application.usecase.SendPasswordUseCase;
import com.dev.torhugo.domain.entity.Account;
import com.dev.torhugo.domain.entity.ForgetPassword;
import com.dev.torhugo.domain.exception.RepositoryException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static util.MessageUtils.MESSAGE_NOT_NULL;
import static util.MessageUtils.MESSAGE_TO_EQUAL;

@IntegrationTest
class SendPasswordUseCaseIT {

    @Autowired
    private SendPasswordUseCase sendPasswordUseCase;
    @Autowired
    private AccountRepository accountRepository;

    @Test
    @Sql(scripts = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void shouldSendPasswordWithSuccess(){
        final var name = "Account Name";
        final var email = "email@example.com";
        final var password = "password@";
        final var newPassword = "new_password@";
        final var account = Account.create(name, email, password);
        accountRepository.save(account);
        final var actualAccount = accountRepository.findByAccountId(account.getAccountId());
        assertNotNull(actualAccount, MESSAGE_NOT_NULL);

        final var input = new UcSendPasswordDTO(email, newPassword);
        sendPasswordUseCase.execute(input);
        final var accountWithNewPassword = accountRepository.findByAccountId(account.getAccountId());
        assertNotNull(accountWithNewPassword, MESSAGE_NOT_NULL);

        assertEquals(account.getAccountId(), accountWithNewPassword.getAccountId(), MESSAGE_TO_EQUAL);
        assertEquals(account.getName(), accountWithNewPassword.getName(), MESSAGE_TO_EQUAL);
        assertEquals(account.getEmail(), accountWithNewPassword.getEmail(), MESSAGE_TO_EQUAL);
        assertEquals(newPassword, accountWithNewPassword.getPassword(), MESSAGE_TO_EQUAL);
        assertNotNull(accountWithNewPassword.getCreatedAt(), MESSAGE_NOT_NULL);
        assertNotNull(accountWithNewPassword.getUpdatedAt(), MESSAGE_NOT_NULL);
    }

    @Test
    void shouldThrowExceptionWhenAccountIsNotFound(){
        final var expectedException = "Account not found!";

        final var input = new UcSendPasswordDTO("example@email.com", "new_password");
        final var exception = assertThrows(RepositoryException.class, () -> sendPasswordUseCase.execute(input));

        assertNotNull(exception);
        assertEquals(expectedException, exception.getMessage(), MESSAGE_TO_EQUAL);
    }
}
