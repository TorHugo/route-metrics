package usecase;

import com.dev.torhugo.application.usecase.FindAccountUseCase;
import com.dev.torhugo.domain.entity.Account;
import com.dev.torhugo.domain.exception.RepositoryException;
import com.dev.torhugo.application.ports.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import util.MessageUtil;

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
        when(accountRepository.findByEmailWithThrow(any())).thenReturn(account);

        // When
        final var result = useCase.execute(account.getEmail());

        // Then
        assertNotNull(result, MESSAGE_NOT_NULL);
        assertEquals(account.getAccountId(), result.accountId(), MESSAGE_TO_EQUAL);
        assertEquals(account.getName(), result.name(), MESSAGE_TO_EQUAL);
        assertEquals(account.getEmail(), result.email(), MESSAGE_TO_EQUAL);
        assertEquals(account.isActive(), result.active(), MESSAGE_TO_EQUAL);
        assertEquals(account.isAdmin(), result.admin(), MESSAGE_TO_EQUAL);
        assertEquals(account.getLastAccess(), result.lastAccess(), MESSAGE_TO_EQUAL);
        assertEquals(account.getCreatedAt(), result.createdAt(), MESSAGE_TO_EQUAL);
        assertEquals(account.getUpdatedAt(), result.updatedAt(), MESSAGE_TO_EQUAL);
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
