package usecase;

import com.dev.torhugo.application.ports.repository.AccountRepository;
import com.dev.torhugo.application.usecase.UpdateLastAccessUseCase;
import com.dev.torhugo.domain.entity.Account;
import com.dev.torhugo.domain.exception.RepositoryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import util.MessageUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UpdateLastAccessUseCaseTest extends MessageUtil {
    @InjectMocks
    UpdateLastAccessUseCase useCase;
    @Mock
    AccountRepository accountRepository;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldUpdateLastAccessWithSuccess(){
        // Given
        final var expectedName = "Account Test";
        final var expectedEmail = "account.test@dev.com.br";
        final var expectedPassword = "Password@";
        final var account = Account.create(expectedName, expectedEmail, expectedPassword);
        when(accountRepository.findByEmailWithThrow(any())).thenReturn(account);
        doNothing().when(accountRepository).save(any());

        // When
        useCase.execute(account.getEmail());

        // Then
        verify(accountRepository, times(1)).findByEmailWithThrow(any());
        verify(accountRepository, times(1)).save(any());
    }

    @Test
    void shouldThrowExceptionWhenAccountNotFound(){
        // Given
        final var expectedException = "Account not found!";
        final var email = "update.account@dev.com.br";
        when(accountRepository.findByEmailWithThrow(any()))
                .thenThrow(new RepositoryException(ACCOUNT_NOT_FOUND));

        // When
        final var exception = assertThrows(RepositoryException.class, () ->
                useCase.execute(email));

        // Then
        assertEquals(expectedException, exception.getMessage(), MESSAGE_TO_EQUAL);
        verify(accountRepository, times(1)).findByEmailWithThrow(any());
        verify(accountRepository, times(0)).save(any());
    }
}
