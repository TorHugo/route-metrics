package usecase;

import com.dev.torhugo.application.ports.repository.AccountRepository;
import com.dev.torhugo.application.usecase.FindAllAccountsUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import util.MessageUtil;

import java.util.List;

import static mock.UseCaseMock.createAccount;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class FindAllAccountsUseCaseTest extends MessageUtil {
    @InjectMocks
    FindAllAccountsUseCase useCase;
    @Mock
    AccountRepository accountRepository;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindAllAccountsWithSuccess(){
        // Given
        final var account = createAccount();
        when(accountRepository.findAll()).thenReturn(List.of(account));

        // When
        final var result = useCase.execute();

        // Then
        assertNotNull(result, MESSAGE_NOT_NULL);

        assertEquals(account.getAccountId(), result.get(0).accountId(), MESSAGE_TO_EQUAL);
        assertEquals(account.getName(), result.get(0).name(), MESSAGE_TO_EQUAL);
        assertEquals(account.getEmail(), result.get(0).email(), MESSAGE_TO_EQUAL);
        assertEquals(account.isActive(), result.get(0).active(), MESSAGE_TO_EQUAL);
        assertEquals(account.isAdmin(), result.get(0).admin(), MESSAGE_TO_EQUAL);
        assertEquals(account.getLastAccess(), result.get(0).lastAccess(), MESSAGE_TO_EQUAL);
        assertEquals(account.getCreatedAt(), result.get(0).createdAt(), MESSAGE_TO_EQUAL);
        assertEquals(account.getUpdatedAt(), result.get(0).updatedAt(), MESSAGE_TO_EQUAL);
        verify(accountRepository, times(1)).findAll();
    }
}
