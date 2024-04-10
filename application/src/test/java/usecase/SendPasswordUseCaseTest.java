package usecase;

import com.dev.torhugo.application.dto.UcSendPasswordDTO;
import com.dev.torhugo.application.ports.repository.AccountRepository;
import com.dev.torhugo.application.usecase.SendPasswordUseCase;
import com.dev.torhugo.domain.exception.RepositoryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import util.MessageUtil;

import static mock.AccountMock.createAccount;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SendPasswordUseCaseTest extends MessageUtil {
    @InjectMocks
    SendPasswordUseCase useCase;
    @Mock
    AccountRepository accountRepository;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldUpdatePasswordWithSuccess(){
        // Given
        final var expectedEmail = "account.test@dev.com.br";
        final var expectedNewPassword = "12345678";
        final var account = createAccount();
        final var input = new UcSendPasswordDTO(expectedEmail, expectedNewPassword);
        when(accountRepository.findByEmailWithThrow(any())).thenReturn(account);
        doNothing().when(accountRepository).save(any());

        // When
        useCase.execute(input);

        // Then
        verify(accountRepository, times(1)).findByEmailWithThrow(any());
        verify(accountRepository, times(1)).save(any());
    }

    @Test
    void shouldThrowExceptionWhenAccountNotFound(){
        // Given
        final var expectedException = "Account not found!";
        final var email = "account.test@dev.com.br";
        final var newPassword = "12345678";
        final var input = new UcSendPasswordDTO(email, newPassword);
        when(accountRepository.findByEmailWithThrow(any()))
                .thenThrow(new RepositoryException(ACCOUNT_NOT_FOUND));

        // When
        final var exception = assertThrows(RepositoryException.class, () ->
                useCase.execute(input));

        // Then
        assertEquals(expectedException, exception.getMessage(), MESSAGE_TO_EQUAL);
        verify(accountRepository, times(1)).findByEmailWithThrow(any());
        verify(accountRepository, times(0)).save(any());
    }
}
