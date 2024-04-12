package usecase;

import com.dev.torhugo.application.ports.messaging.QueueProducer;
import com.dev.torhugo.application.ports.repository.AccountRepository;
import com.dev.torhugo.application.ports.repository.ForgetPasswordRepository;
import com.dev.torhugo.application.usecase.SendHashUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import util.MessageUtil;

import static mock.UseCaseMock.createAccount;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SendHashUseCaseTest extends MessageUtil {

    @InjectMocks
    SendHashUseCase sendHashUseCase;

    @Mock
    AccountRepository accountRepository;

    @Mock
    ForgetPasswordRepository forgetPasswordRepository;

    @Mock
    QueueProducer queueProducer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSendHashCodeWithSuccess() {
        final var expectedEmail = "account@example.com";
        final var expectedAccount = createAccount();
        when(accountRepository.findByEmailWithThrow(any())).thenReturn(expectedAccount);
        doNothing().when(forgetPasswordRepository).save(any());
        doNothing().when(queueProducer).sendMessage(any(), any());

        sendHashUseCase.execute(expectedEmail);

        verify(accountRepository, times(1)).findByEmailWithThrow(any());
        verify(forgetPasswordRepository, times(1)).save(any());
        verify(queueProducer, times(1)).sendMessage(any(), any());
    }
}
