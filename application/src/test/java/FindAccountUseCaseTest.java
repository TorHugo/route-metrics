import com.dev.torhugo.FindAccountUseCase;
import com.dev.torhugo.domain.entity.Account;
import com.dev.torhugo.domain.error.exception.RepositoryNotFoundError;
import com.dev.torhugo.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import util.MessageUtil;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class FindAccountUseCaseTest extends MessageUtil {
    @InjectMocks
    FindAccountUseCase useCase;
    @Mock
    AccountRepository accountRepository;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindAccountWhenIsValidAccountId(){
        // Given
        final var expectedName = "Account Test";
        final var expectedEmail = "account.test@dev.com.br";
        final var expectedPassword = "Password@";
        final var account = Account.create(expectedName, expectedEmail, expectedPassword);
        when(accountRepository.findByAccountId(any())).thenReturn(account);

        // When
        final var result = useCase.execute(account.getAccountId());

        // Then
        assertNotNull(result, messageNotNull);
        verify(accountRepository, times(1)).findByAccountId(any());
    }

    @Test
    void shouldThrowExceptionWhenAccountNotFound(){
        // Given
        final var expectedException = "Account not found!";
        final var expectedAccountId = UUID.randomUUID();
        when(accountRepository.findByAccountId(any())).thenReturn(null);

        // When
        final var exception = assertThrows(RepositoryNotFoundError.class, () ->
                useCase.execute(expectedAccountId));

        // Then
        assertEquals(expectedException, exception.getMessage(), messageToEqual);
        verify(accountRepository, times(1)).findByAccountId(any());
    }
}
