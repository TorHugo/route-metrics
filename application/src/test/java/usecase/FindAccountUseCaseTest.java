package usecase;

import com.dev.torhugo.application.ports.repository.AccountRepository;
import com.dev.torhugo.application.usecase.FindAccountUseCase;
import com.dev.torhugo.domain.exception.RepositoryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import util.MessageUtil;

import static mock.AccountMock.createAccount;
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
        final var account = createAccount();
        when(accountRepository.findByEmailWithThrow(any())).thenReturn(account);

        // When
        final var result = useCase.execute(account.getEmail());

        // Then
        assertNotNull(result, MESSAGE_NOT_NULL);
        assertEquals(account.getAccountId(), result.getAccountId(), MESSAGE_TO_EQUAL);
        assertEquals(account.getName(), result.getName(), MESSAGE_TO_EQUAL);
        assertEquals(account.getEmail(), result.getEmail(), MESSAGE_TO_EQUAL);
        assertEquals(account.isActive(), result.isActive(), MESSAGE_TO_EQUAL);
        assertEquals(account.isAdmin(), result.isAdmin(), MESSAGE_TO_EQUAL);
        assertEquals(account.getLastAccess(), result.getLastAccess(), MESSAGE_TO_EQUAL);
        assertEquals(account.getCreatedAt(), result.getCreatedAt(), MESSAGE_TO_EQUAL);
        assertEquals(account.getUpdatedAt(), result.getUpdatedAt(), MESSAGE_TO_EQUAL);
        verify(accountRepository, times(1)).findByEmailWithThrow(any());
    }

    @Test
    void shouldThrowExceptionWhenAccountNotFound(){
        // Given
        final var expectedException = "Account not found!";
        final var expectedAccountEmail = "email@gmail.com";
        when(accountRepository.findByEmailWithThrow(any()))
                .thenThrow(new RepositoryException(ACCOUNT_NOT_FOUND));

        // When
        final var exception = assertThrows(RepositoryException.class, () ->
                useCase.execute(expectedAccountEmail));

        // Then
        assertEquals(expectedException, exception.getMessage(), MESSAGE_TO_EQUAL);
        verify(accountRepository, times(1)).findByEmailWithThrow(any());
    }
}
