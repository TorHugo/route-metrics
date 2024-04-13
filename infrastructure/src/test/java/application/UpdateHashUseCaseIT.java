package application;

import annotation.IntegrationTest;
import com.dev.torhugo.application.ports.repository.ForgetPasswordRepository;
import com.dev.torhugo.application.usecase.UpdateHashUseCase;
import com.dev.torhugo.domain.entity.ForgetPassword;
import com.dev.torhugo.domain.exception.InvalidHashForgetPasswordException;
import com.dev.torhugo.domain.exception.RepositoryException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static util.MessageUtils.*;

@IntegrationTest
class UpdateHashUseCaseIT {

    @Autowired
    private UpdateHashUseCase updateHashUseCase;

    @Autowired
    private ForgetPasswordRepository forgetPasswordRepository;

    @Test
    @Sql(scripts = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void shouldUpdateHashWithSuccess(){
        final var accountId = UUID.randomUUID();
        final var forgetPassword = ForgetPassword.restore(UUID.randomUUID(), accountId, "123456", LocalDateTime.now(), true, true, LocalDateTime.now(), null);
        forgetPasswordRepository.save(forgetPassword);
        final var entity = forgetPasswordRepository.findHashByAccount(accountId);
        assertNotNull(entity, MESSAGE_NOT_NULL);

        updateHashUseCase.execute(entity.getHashCode(), entity.getAccountId());
        final var updateHashEntity = forgetPasswordRepository.findHashByAccount(accountId);

        assertNotNull(updateHashEntity, MESSAGE_NOT_NULL);
        assertFalse(updateHashEntity.isActive(), MESSAGE_FALSE);
        assertTrue(updateHashEntity.isConfirmed(), MESSAGE_TRUE);
    }

    @Test
    @Sql(scripts = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void shouldThrowExceptionWhenHashCodeIsNotConfirmed(){
        final var expectedException = "HashCode is not confirmed!";
        final var accountId = UUID.randomUUID();
        final var forgetPassword = ForgetPassword.restore(UUID.randomUUID(), accountId, "123456", LocalDateTime.now(), true, false, LocalDateTime.now(), null);
        forgetPasswordRepository.save(forgetPassword);
        final var entity = forgetPasswordRepository.findHashByAccount(accountId);
        assertNotNull(entity, MESSAGE_NOT_NULL);


        final var exception = assertThrows(InvalidHashForgetPasswordException.class, ()-> updateHashUseCase.execute(entity.getHashCode(), entity.getAccountId()));
        final var updateHashEntity = forgetPasswordRepository.findHashByAccount(accountId);

        assertNotNull(exception, MESSAGE_NOT_NULL);
        assertNotNull(updateHashEntity, MESSAGE_NOT_NULL);
        assertEquals(expectedException, exception.getMessage(), MESSAGE_TO_EQUAL);
        assertTrue(updateHashEntity.isActive(), MESSAGE_TRUE);
        assertFalse(updateHashEntity.isConfirmed(), MESSAGE_TRUE);
    }

    @Test
    @Sql(scripts = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void shouldThrowExceptionWhenHashCodeConfirmedNotFound(){
        final var expectedException = "HashCode not found!";
        final var accountId = UUID.randomUUID();

        final var exception = assertThrows(RepositoryException.class, ()-> updateHashUseCase.execute("123456", accountId));
        final var allHash = forgetPasswordRepository.findAll();

        assertNotNull(exception, MESSAGE_NOT_NULL);
        assertTrue(allHash.isEmpty(), MESSAGE_TRUE);
        assertEquals(expectedException, exception.getMessage(), MESSAGE_TO_EQUAL);
    }
}
