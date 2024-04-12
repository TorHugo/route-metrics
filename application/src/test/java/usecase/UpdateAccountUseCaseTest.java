package usecase;

import com.dev.torhugo.application.dto.UcUpdateAccountDTO;
import com.dev.torhugo.application.ports.repository.AccountRepository;
import com.dev.torhugo.application.usecase.UpdateAccountUseCase;
import com.dev.torhugo.domain.exception.RepositoryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import util.MessageUtil;

import java.util.UUID;

import static mock.UseCaseMock.createAccount;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UpdateAccountUseCaseTest extends MessageUtil {
    @InjectMocks
    UpdateAccountUseCase useCase;
    @Mock
    AccountRepository accountRepository;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldUpdateAccountWhenGivenParametersIsNull(){
        // Given
        final var expectedNewName = "Update Account";
        final var expectedNewEmail = "update.account@dev.com.br";
        final var account = createAccount();
        final var input = new UcUpdateAccountDTO(account.getAccountId(), expectedNewName, expectedNewEmail, null, true, false);
        when(accountRepository.findByAccountId(any())).thenReturn(account);
        doNothing().when(accountRepository).save(any());

        // When
        final var result = useCase.execute(input);

        // Then
        assertNotNull(result, MESSAGE_NOT_NULL);
        assertEquals(account.getAccountId(), result.getAccountId(), MESSAGE_TO_EQUAL);
        assertEquals(expectedNewName, result.getName(), MESSAGE_TO_EQUAL);
        assertEquals(expectedNewEmail, result.getEmail(), MESSAGE_TO_EQUAL);
        assertEquals(account.isActive(), result.isActive(), MESSAGE_TO_EQUAL);
        assertEquals(account.isAdmin(), result.isAdmin(), MESSAGE_TO_EQUAL);
        assertEquals(account.getLastAccess(), result.getLastAccess(), MESSAGE_TO_EQUAL);
        assertEquals(account.getCreatedAt(), result.getCreatedAt(), MESSAGE_TO_EQUAL);
        assertNotNull(result.getUpdatedAt(), MESSAGE_NOT_NULL);
        verify(accountRepository, times(1)).findByAccountId(any());
        verify(accountRepository, times(1)).save(any());
    }

    @Test
    void shouldUpdateAccountWhenValuesOfInputIsNotNull(){
        // Given
        final var expectedNewName = "Update Account";
        final var expectedNewEmail = "update.account@dev.com.br";
        final var expectedPassword = "password@";
        final var account = createAccount();
        final var input = new UcUpdateAccountDTO(account.getAccountId(), expectedNewName, expectedNewEmail, expectedPassword, true, false);
        when(accountRepository.findByAccountId(any())).thenReturn(account);
        doNothing().when(accountRepository).save(any());

        // When
        final var result = useCase.execute(input);

        // Then
        assertNotNull(result, MESSAGE_NOT_NULL);
        assertEquals(account.getAccountId(), result.getAccountId(), MESSAGE_TO_EQUAL);
        assertEquals(expectedNewName, result.getName(), MESSAGE_TO_EQUAL);
        assertEquals(expectedNewEmail, result.getEmail(), MESSAGE_TO_EQUAL);
        assertEquals(account.isActive(), result.isActive(), MESSAGE_TO_EQUAL);
        assertEquals(account.isAdmin(), result.isAdmin(), MESSAGE_TO_EQUAL);
        assertEquals(account.getLastAccess(), result.getLastAccess(), MESSAGE_TO_EQUAL);
        assertEquals(account.getCreatedAt(), result.getCreatedAt(), MESSAGE_TO_EQUAL);
        assertNotNull(result.getUpdatedAt(), MESSAGE_NOT_NULL);
        verify(accountRepository, times(1)).findByAccountId(any());
        verify(accountRepository, times(1)).save(any());
    }

    @Test
    void shouldUpdateAccountWhenGivenAllParametersIsNull(){
        // Given
        final var account = createAccount();
        final var input = new UcUpdateAccountDTO(account.getAccountId(), null, null, null, true, false);
        when(accountRepository.findByAccountId(any())).thenReturn(account);
        doNothing().when(accountRepository).save(any());

        // When
        final var result = useCase.execute(input);

        // Then
        assertNotNull(result, MESSAGE_NOT_NULL);
        assertEquals(account.getAccountId(), result.getAccountId(), MESSAGE_TO_EQUAL);
        assertEquals(account.getName(), result.getName(), MESSAGE_TO_EQUAL);
        assertEquals(account.getEmail(), result.getEmail(), MESSAGE_TO_EQUAL);
        assertEquals(account.isActive(), result.isActive(), MESSAGE_TO_EQUAL);
        assertEquals(account.isAdmin(), result.isAdmin(), MESSAGE_TO_EQUAL);
        assertEquals(account.getLastAccess(), result.getLastAccess(), MESSAGE_TO_EQUAL);
        assertEquals(account.getCreatedAt(), result.getCreatedAt(), MESSAGE_TO_EQUAL);
        assertNotNull(result.getUpdatedAt(), MESSAGE_NOT_NULL);
        verify(accountRepository, times(1)).findByAccountId(any());
        verify(accountRepository, times(1)).save(any());
    }

    @Test
    void shouldThrowExceptionWhenAccountNotFound(){
        // Given
        final var expectedException = "Account not found!";
        final var expectedNewName = "Update Account";
        final var expectedNewEmail = "update.account@dev.com.br";
        final var input = new UcUpdateAccountDTO(UUID.randomUUID(), expectedNewName, expectedNewEmail, null, true, false);
        when(accountRepository.findByAccountId(any()))
                .thenThrow(new RepositoryException(ACCOUNT_NOT_FOUND));

        // When
        final var exception = assertThrows(RepositoryException.class, () ->
                useCase.execute(input));

        // Then
        assertEquals(expectedException, exception.getMessage(), MESSAGE_TO_EQUAL);
        verify(accountRepository, times(1)).findByAccountId(any());
        verify(accountRepository, times(0)).save(any());
    }
}
