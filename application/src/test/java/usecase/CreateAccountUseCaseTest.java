package usecase;

import com.dev.torhugo.application.dto.UcAccountDTO;
import com.dev.torhugo.application.ports.messaging.QueueProducer;
import com.dev.torhugo.application.ports.repository.AccountRepository;
import com.dev.torhugo.application.usecase.CreateAccountUseCase;
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

class CreateAccountUseCaseTest extends MessageUtil {
    @InjectMocks
    CreateAccountUseCase useCase;
    @Mock
    AccountRepository accountRepository;
    @Mock
    QueueProducer queueProducer;

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
        final var input = new UcAccountDTO(expectedName, expectedEmail, expectedPassword);
        when(accountRepository.findByEmail(expectedEmail)).thenReturn(null);
        doNothing().when(accountRepository).save(any());
        doNothing().when(queueProducer).sendMessage(any(), any());

        // When
        final var result = useCase.execute(input);

        // Then
        assertNotNull(result, MESSAGE_NOT_NULL);
        verify(accountRepository, times(1)).findByEmail(any());
        verify(queueProducer, times(1)).sendMessage(any(), any());
        verify(accountRepository, times(1)).save(any());
    }

    @Test
    void shouldThrowsExceptionWhenAccountAlreadyExists(){
        // Given
        final var expectedMessageError = "Account already exists!";
        final var expectedName = "Account Test";
        final var expectedEmail = "account.test@dev.com.br";
        final var expectedPassword = "Password@";
        final var input = new UcAccountDTO(expectedName, expectedEmail, expectedPassword);
        when(accountRepository.findByEmail(expectedEmail)).thenReturn(createAccount());
        doNothing().when(accountRepository).save(any());
        doNothing().when(queueProducer).sendMessage(any(), any());

        // When
        final var exception = assertThrows(InvalidArgumentException.class, () ->
                useCase.execute(input)
        );

        // Then
        assertEquals(expectedMessageError, exception.getMessage(), MESSAGE_TO_EQUAL);
        verify(accountRepository, times(1)).findByEmail(any());
        verify(queueProducer, times(0)).sendMessage(any(), any());
        verify(accountRepository, times(0)).save(any());
    }
}
