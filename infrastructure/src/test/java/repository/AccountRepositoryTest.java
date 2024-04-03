package repository;

import com.dev.torhugo.domain.entity.Account;
import com.dev.torhugo.repository.AccountRepository;
import config.AnnotationDefaultIT;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AccountRepositoryTest extends AnnotationDefaultIT {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    @Sql(scripts = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void shouldSaveAndGivenAsAccount(){
        // Given
        final var expectedName = "Account Test";
        final var expectedEmail = "account.test@dev.com.br";
        final var expectedPassword = "Password@";
        final var expectedAccount = Account.create(expectedName, expectedEmail, expectedPassword);

        // When
        accountRepository.save(expectedAccount);
        final var account = accountRepository.findByAccountId(expectedAccount.getAccountId());

        // Then
        assertNotNull(account, MESSAGE_NOT_NULL);
        assertEquals(expectedAccount.getAccountId(), account.getAccountId(), MESSAGE_TO_EQUAL);
        assertEquals(expectedAccount.getName(), account.getName(), MESSAGE_TO_EQUAL);
        assertEquals(expectedAccount.getEmail(), account.getEmail(), MESSAGE_TO_EQUAL);
        assertEquals(expectedAccount.getPassword(), account.getPassword(), MESSAGE_TO_EQUAL);
        assertEquals(expectedAccount.isActive(), account.isActive(), MESSAGE_TO_EQUAL);
        assertEquals(expectedAccount.isAdmin(), account.isAdmin(), MESSAGE_TO_EQUAL);
        assertNotNull(account.getLastAccess(), MESSAGE_NOT_NULL);
        assertNotNull(account.getCreatedAt(), MESSAGE_NOT_NULL);
        assertNull(account.getUpdatedAt(), MESSAGE_NULL);
    }

    @Test
    @Sql(scripts = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void shouldFindAccountByEmailWithSuccess(){
        // Given
        final var expectedName = "Account Test";
        final var expectedEmail = "account.test@dev.com.br";
        final var expectedPassword = "Password@";
        final var expectedAccount = Account.create(expectedName, expectedEmail, expectedPassword);

        // When
        accountRepository.save(expectedAccount);
        final var account = accountRepository.findByEmail(expectedEmail);

        // Then
        assertNotNull(account, MESSAGE_NOT_NULL);
        assertEquals(expectedAccount.getAccountId(), account.getAccountId(), MESSAGE_TO_EQUAL);
        assertEquals(expectedAccount.getName(), account.getName(), MESSAGE_TO_EQUAL);
        assertEquals(expectedAccount.getEmail(), account.getEmail(), MESSAGE_TO_EQUAL);
        assertEquals(expectedAccount.getPassword(), account.getPassword(), MESSAGE_TO_EQUAL);
        assertEquals(expectedAccount.isActive(), account.isActive(), MESSAGE_TO_EQUAL);
        assertEquals(expectedAccount.isAdmin(), account.isAdmin(), MESSAGE_TO_EQUAL);
        assertNotNull(account.getLastAccess(), MESSAGE_NOT_NULL);
        assertNotNull(account.getCreatedAt(), MESSAGE_NOT_NULL);
        assertNull(account.getUpdatedAt(), MESSAGE_NULL);
    }

    @Test
    @Sql(scripts = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void notShouldFindAccountByEmailWhenAccountIsNotSaved(){
        // Given && When
        final var expectedEmail = "";
        final var account = accountRepository.findByEmail(expectedEmail);

        // Then
        assertNull(account, MESSAGE_NULL);
    }

    @Test
    @Sql(scripts = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void notShouldFindAccountByIdWhenAccountIsNotSaved(){
        // Given && When
        final var expectedAccountId = UUID.randomUUID();
        final var account = accountRepository.findByAccountId(expectedAccountId);

        // Then
        assertNull(account, MESSAGE_NULL);
    }
}
