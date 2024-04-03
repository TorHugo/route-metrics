import com.dev.torhugo.CreateAccountUseCase;
import com.dev.torhugo.domain.entity.Account;
import com.dev.torhugo.domain.error.exception.InvalidArgumentError;
import com.dev.torhugo.messaging.QueueProducer;
import com.dev.torhugo.models.AccountDTO;
import com.dev.torhugo.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import util.MessageUtil;

import java.util.Objects;

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
        final var input = new AccountDTO(expectedName, expectedEmail, expectedPassword);
        when(accountRepository.findByEmail(expectedEmail)).thenReturn(null);
        doNothing().when(accountRepository).save(any());
        doNothing().when(queueProducer).sendMessage(any(), any());

        // When
        final var result = useCase.execute(input);

        // Then
        assertNotNull(result, messageNotNull);
        verify(accountRepository, times(1)).findByEmail(any());
        verify(queueProducer, times(1)).sendMessage(any(), any());
        verify(accountRepository, times(1)).save(argThat(account ->
                Objects.nonNull(account.getAccountId())
                        && Objects.equals(expectedName, account.getName())
                        && Objects.equals(expectedEmail, account.getEmail())
                        && Objects.equals(expectedPassword, account.getPassword())
                        && Objects.equals(true, account.isActive())
                        && Objects.equals(false, account.isAdmin())
                        && Objects.nonNull(account.getCreatedAt())
                        && Objects.nonNull(account.getLastAccess())
                        && Objects.isNull(account.getUpdatedAt())
        ));
    }

    @Test
    void shouldThrowsExceptionWhenAccountAlreadyExists(){
        // Given
        final var expectedMessageError = "Account already exists!";
        final var expectedName = "Account Test";
        final var expectedEmail = "account.test@dev.com.br";
        final var expectedPassword = "Password@";
        final var expectedAccount = Account.create(
                expectedName,
                expectedEmail,
                expectedPassword
        );
        final var input = new AccountDTO(expectedName, expectedEmail, expectedPassword);
        when(accountRepository.findByEmail(expectedEmail)).thenReturn(expectedAccount);
        doNothing().when(accountRepository).save(any());
        doNothing().when(queueProducer).sendMessage(any(), any());

        // When
        final var exception = assertThrows(InvalidArgumentError.class, () ->
                useCase.execute(input)
        );

        // Then
        assertEquals(expectedMessageError, exception.getMessage(), messageToEqual);
        verify(accountRepository, times(1)).findByEmail(any());
        verify(queueProducer, times(0)).sendMessage(any(), any());
        verify(accountRepository, times(0)).save(any());
    }
}
