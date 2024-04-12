package usecase;

import com.dev.torhugo.application.dto.UcInativateAccountDTO;
import com.dev.torhugo.application.ports.repository.AccountRepository;
import com.dev.torhugo.application.usecase.InativateAccountUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import util.MessageUtil;

import java.util.List;

import static mock.UseCaseMock.createAccount;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class InativateAccountUseCaseTest extends MessageUtil {
    @InjectMocks
    InativateAccountUseCase useCase;

    @Mock
    AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldInativatePasswordWithSuccess(){
        // Given
        final var account = createAccount();
        final var input = new UcInativateAccountDTO(List.of(account.getAccountId()));
        when(accountRepository.findAllByIds(any())).thenReturn(List.of(account));

        // When
        useCase.execute(input);

        // Then
        verify(accountRepository, times(1)).findAllByIds(any());
        verify(accountRepository, times(1)).saveAll(any());
    }
}
