package application;

import annotation.IntegrationTest;
import com.dev.torhugo.application.dto.UcSendPasswordDTO;
import com.dev.torhugo.application.ports.repository.AccountRepository;
import com.dev.torhugo.application.ports.repository.ForgetPasswordRepository;
import com.dev.torhugo.application.usecase.ConfirmHashUseCase;
import com.dev.torhugo.application.usecase.SendHashUseCase;
import com.dev.torhugo.application.usecase.SendPasswordUseCase;
import com.dev.torhugo.application.usecase.UpdateHashUseCase;
import com.dev.torhugo.domain.entity.Account;
import com.dev.torhugo.infrastructure.repository.database.ForgetPasswordJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;
import static util.MessageUtils.*;

@IntegrationTest
class ForgetPasswordUseCaseIT {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ForgetPasswordRepository forgetPasswordRepository;
    @Autowired
    private SendHashUseCase sendHashUseCase;
    @Autowired
    private ConfirmHashUseCase confirmHashUseCase;
    @Autowired
    private UpdateHashUseCase updateHashUseCase;
    @Autowired
    private SendPasswordUseCase sendPasswordUseCase;

    @Test
    @Sql(scripts = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void shouldSendHashWhenIsValidAccount() {
        final var name = "Account Name";
        final var email = "example@example.com";
        final var password = "password@";
        final var account = Account.create(name, email, password);
        accountRepository.save(account);
        final var actualAccount = accountRepository.findByEmail(account.getEmail());
        assertNotNull(actualAccount, MESSAGE_NOT_NULL);

        sendHashUseCase.execute(actualAccount.getEmail());
        final var sendHashEntity = forgetPasswordRepository.findHashByAccount(actualAccount.getAccountId());
        assertNotNull(sendHashEntity, MESSAGE_NOT_NULL);
        assertNotNull(sendHashEntity.getHashCode(), MESSAGE_NOT_NULL);
        assertTrue(sendHashEntity.isActive(), MESSAGE_TRUE);
        assertFalse(sendHashEntity.isConfirmed(), MESSAGE_FALSE);

        confirmHashUseCase.execute(sendHashEntity.getHashCode(), sendHashEntity.getAccountId());
        final var confirmHashEntity = forgetPasswordRepository.findHashByAccount(actualAccount.getAccountId());
        assertNotNull(confirmHashEntity, MESSAGE_NOT_NULL);
        assertTrue(confirmHashEntity.isActive(), MESSAGE_TRUE);
        assertTrue(confirmHashEntity.isConfirmed(), MESSAGE_TRUE);

        updateHashUseCase.execute(confirmHashEntity.getHashCode(), confirmHashEntity.getAccountId());
        final var updateHashEntity = forgetPasswordRepository.findHashByAccount(actualAccount.getAccountId());
        assertNotNull(updateHashEntity, MESSAGE_NOT_NULL);
        assertFalse(updateHashEntity.isActive(), MESSAGE_FALSE);
        assertTrue(updateHashEntity.isConfirmed(), MESSAGE_TRUE);

        final var newPassword = "new_password@";
        final var sendPassword = new UcSendPasswordDTO(email, newPassword);
        sendPasswordUseCase.execute(sendPassword);
        final var sendPasswordEntity = forgetPasswordRepository.findHashByAccount(actualAccount.getAccountId());
        assertNotNull(sendPasswordEntity, MESSAGE_NOT_NULL);
        assertFalse(updateHashEntity.isActive(), MESSAGE_FALSE);
        assertTrue(updateHashEntity.isConfirmed(), MESSAGE_TRUE);

        final var accountUpdated = accountRepository.findByAccountId(account.getAccountId());
        assertEquals(newPassword, accountUpdated.getPassword(), MESSAGE_TO_EQUAL);
    }
}
