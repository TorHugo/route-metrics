package usecase;

import com.dev.torhugo.application.dto.UcUpdatePasswordDTO;
import com.dev.torhugo.application.ports.repository.AccountRepository;
import com.dev.torhugo.application.usecase.UpdatePasswordUseCase;
import com.dev.torhugo.domain.exception.InvalidArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import util.MessageUtil;

import static mock.UseCaseMock.createAccount;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UpdatePasswordUseCaseTest extends MessageUtil {

    @InjectMocks
    UpdatePasswordUseCase updatePasswordUseCase;

    @Mock
    AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldUpdatePasswordUseCaseWithSuccess(){
        final var expectedTrue = true;
        final var expectedAccount = createAccount();
        final var expectedNewPassword = "admin12345";
        final var input = new UcUpdatePasswordDTO(expectedNewPassword, expectedTrue);
        doNothing().when(accountRepository).save(any());

        updatePasswordUseCase.execute(expectedAccount, input);

        verify(accountRepository, times(1)).save(any());
    }
    @Test
    void shouldThrowExceptionUpdatePasswordWhenInvalidPassword(){
        final var expectedMessageError = "The password sent is not the same as the current password!";
        final var expectedFalse = false;
        final var expectedAccount = createAccount();
        final var expectedNewPassword = "admin12345";
        final var input = new UcUpdatePasswordDTO(expectedNewPassword, expectedFalse);
        doNothing().when(accountRepository).save(any());

        final var result = assertThrows(InvalidArgumentException.class, () -> updatePasswordUseCase.execute(expectedAccount, input));

        assertNotNull(result, MESSAGE_NOT_NULL);
        assertEquals(expectedMessageError, result.getMessage(), MESSAGE_TO_EQUAL);
        verify(accountRepository, times(0)).save(any());
    }
}
