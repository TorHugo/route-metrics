package application;

import annotation.IntegrationTest;
import com.dev.torhugo.application.ports.repository.ForgetPasswordRepository;
import com.dev.torhugo.application.usecase.ConfirmHashUseCase;
import com.dev.torhugo.domain.entity.ForgetPassword;
import com.dev.torhugo.domain.exception.InvalidHashForgetPasswordException;
import com.dev.torhugo.domain.exception.RepositoryException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.MessageUtils.*;

@IntegrationTest
class ConfirmHashCodeIT {

    @Autowired
    private ConfirmHashUseCase confirmHashUseCase;

    @Autowired
    private ForgetPasswordRepository forgetPasswordRepository;

    @Test
    @Sql(scripts = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void shouldConfirmHashWithSuccess(){
        final var account = UUID.randomUUID();
        final var forgetPassword = ForgetPassword.create(account);
        forgetPasswordRepository.save(forgetPassword);
        final var entity = forgetPasswordRepository.findHashByAccount(account);
        assertNotNull(entity, MESSAGE_NOT_NULL);

        confirmHashUseCase.execute(entity.getHashCode(), entity.getAccountId());
        final var actualEntity = forgetPasswordRepository.findHashByAccount(entity.getAccountId());
        assertNotNull(actualEntity, MESSAGE_NOT_NULL);
        assertTrue(actualEntity.isActive(), MESSAGE_TRUE);
        assertTrue(actualEntity.isConfirmed(), MESSAGE_TRUE);
    }

    @Test
    @Sql(scripts = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void shouldThrowExceptionWhenHashCodeNotFound(){
        final var hashCode = "example@example.com";
        final var account = UUID.randomUUID();
        final var expectedException = "HashCode not found!";

        final var exception = assertThrows(RepositoryException.class, () -> confirmHashUseCase.execute(hashCode, account));
        final var allHash = forgetPasswordRepository.findAll();

        assertNotNull(exception, MESSAGE_NOT_NULL);
        assertEquals(expectedException, exception.getMessage(), MESSAGE_TO_EQUAL);
        assertTrue(allHash.isEmpty(), MESSAGE_TRUE);
    }

    @Test
    @Sql(scripts = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void shouldThrowExceptionWhenHashCodeIsExpirated(){
        final var expectedException = "This hash is expired!";
        final var accountId = UUID.randomUUID();
        final var forgetPassword = ForgetPassword.restore(UUID.randomUUID(), accountId, "123456", LocalDateTime.now().minusDays(1L), true, false, LocalDateTime.now().minusDays(1L), null);
        forgetPasswordRepository.save(forgetPassword);
        final var entity = forgetPasswordRepository.findHashByAccount(accountId);
        assertNotNull(entity, MESSAGE_NOT_NULL);

        final var exception = assertThrows(InvalidHashForgetPasswordException.class, () -> confirmHashUseCase.execute(forgetPassword.getHashCode(), forgetPassword.getAccountId()));

        assertNotNull(exception, MESSAGE_NOT_NULL);
        assertEquals(expectedException, exception.getMessage(), MESSAGE_TO_EQUAL);
    }
}
