package repository;

import com.dev.torhugo.domain.entity.Account;
import com.dev.torhugo.repository.AccountRepository;
import config.AnnotationDefaultIT;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

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
        assertNotNull(account, messageNotNull);
        assertEquals(expectedAccount.getAccountId(), account.getAccountId(), messageToEqual);
        assertEquals(expectedAccount.getName(), account.getName(), messageToEqual);
        assertEquals(expectedAccount.getEmail(), account.getEmail(), messageToEqual);
        assertEquals(expectedAccount.getPassword(), account.getPassword(), messageToEqual);
        assertEquals(expectedAccount.isActive(), account.isActive(), messageToEqual);
        assertEquals(expectedAccount.isAdmin(), account.isAdmin(), messageToEqual);
        assertNotNull(account.getLastAccess(), messageNotNull);
        assertNotNull(account.getCreatedAt(), messageNotNull);
        assertNull(account.getUpdatedAt(), messageNull);
    }
}
