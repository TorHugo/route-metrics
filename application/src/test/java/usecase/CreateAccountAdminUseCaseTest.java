package usecase;

import com.dev.torhugo.application.dto.UcAccountAdminDTO;
import com.dev.torhugo.application.ports.repository.AccountRepository;
import com.dev.torhugo.application.usecase.CreateAccountAdminUseCase;
import com.dev.torhugo.domain.entity.Account;
import com.dev.torhugo.domain.exception.InvalidArgumentException;
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

class CreateAccountAdminUseCaseTest extends MessageUtil {
    @InjectMocks
    CreateAccountAdminUseCase useCase;
    @Mock
    AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldExecuteUseCaseWithSuccess(){
        // Given
        final var expectedName = "Account Test";
        final var expectedEmail = "account.test@dev.com.br";
        final var expectedPassword = "Password@";
        final var expectedActive = false;
        final var expectedAdmin = true;
        final var input = new UcAccountAdminDTO(expectedName, expectedEmail, expectedPassword, expectedActive, expectedAdmin);
        when(accountRepository.findByEmail(expectedEmail)).thenReturn(null);
        doNothing().when(accountRepository).save(any());

        // When
        final var result = useCase.execute(input);

        // Then
        assertNotNull(result, MESSAGE_NOT_NULL);
        verify(accountRepository, times(1)).findByEmail(any());
        verify(accountRepository, times(1)).save(any());
    }

    @Test
    void shouldThrowsExceptionWhenAccountAlreadyExists(){
        // Given
        final var expectedMessageError = "Account already exists!";
        final var expectedName = "Account Test";
        final var expectedEmail = "account.test@dev.com.br";
        final var expectedPassword = "Password@";
        final var expectedActive = false;
        final var expectedAdmin = true;
        final var input = new UcAccountAdminDTO(expectedName, expectedEmail, expectedPassword, expectedActive, expectedAdmin);
        when(accountRepository.findByEmail(expectedEmail)).thenReturn(createAccount());
        doNothing().when(accountRepository).save(any());

        // When
        final var exception = assertThrows(InvalidArgumentException.class, () ->
                useCase.execute(input)
        );

        // Then
        assertEquals(expectedMessageError, exception.getMessage(), MESSAGE_TO_EQUAL);
        verify(accountRepository, times(1)).findByEmail(any());
        verify(accountRepository, times(0)).save(any());
    }
}
