package application;

import annotation.IntegrationTest;
import com.dev.torhugo.application.ports.repository.AccountRepository;
import com.dev.torhugo.application.ports.repository.ForgetPasswordRepository;
import com.dev.torhugo.application.usecase.SendHashUseCase;
import com.dev.torhugo.domain.entity.Account;
import com.dev.torhugo.domain.exception.RepositoryException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;
import static util.MessageUtils.*;

@IntegrationTest
class SendHashCodeIT {

    @Autowired
    private SendHashUseCase sendHashUseCase;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ForgetPasswordRepository forgetPasswordRepository;

    @Test
    @Sql(scripts = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void shoudSendHashCodeWithSuccess(){
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
        assertEquals(actualAccount.getAccountId(), sendHashEntity.getAccountId(), MESSAGE_TO_EQUAL);
        assertNotNull(sendHashEntity.getHashCode(), MESSAGE_NOT_NULL);
        assertTrue(sendHashEntity.isActive(), MESSAGE_TRUE);
        assertFalse(sendHashEntity.isConfirmed(), MESSAGE_FALSE);
    }

    @Test
    @Sql(scripts = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void shouldThrowExceptionWhenAccountIsNotFound() {
        final var email = "example@example.com";
        final var expectedException = "Account not found!";

        final var exception = assertThrows(RepositoryException.class, () -> sendHashUseCase.execute(email));
        final var allHash = forgetPasswordRepository.findAll();

        assertNotNull(exception, MESSAGE_NOT_NULL);
        assertEquals(expectedException, exception.getMessage(), MESSAGE_TO_EQUAL);
        assertTrue(allHash.isEmpty(), MESSAGE_TRUE);
    }
}
