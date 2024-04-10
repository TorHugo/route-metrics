package usecase;

import com.dev.torhugo.application.ports.repository.ForgetPasswordRepository;
import com.dev.torhugo.application.usecase.ConfirmHashUseCase;
import com.dev.torhugo.domain.entity.ForgetPassword;
import com.dev.torhugo.domain.exception.InvalidHashForgetPasswordException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import util.MessageUtil;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ConfirmeHashUseCaseTest extends MessageUtil {
    @InjectMocks
    ConfirmHashUseCase confirmHashUseCase;

    @Mock
    ForgetPasswordRepository forgetPasswordRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldConfirmHashWithSuccess(){
        final var expectedAccountId = UUID.randomUUID();
        final var expectedForgetPassword = ForgetPassword.create(expectedAccountId);
        when(forgetPasswordRepository.findHashConfirmedFalse(any(), any())).thenReturn(expectedForgetPassword);
        doNothing().when(forgetPasswordRepository).save(any());

        confirmHashUseCase.execute(expectedForgetPassword.getHashCode(), expectedAccountId);

        verify(forgetPasswordRepository, times(1)).findHashConfirmedFalse(any(), any());
        verify(forgetPasswordRepository, times(1)).save(any());
    }

    @Test
    void shouldThrowExceptionWhenHashCodeIsBeforeANow(){
        final var expectedMessageError = "This hash is expired!";
        final var expectedForgetPasswordId = UUID.randomUUID();
        final var expectedAccountId = UUID.randomUUID();
        final var expectedHashCode = "123456";
        final var expectedExpirationDate = LocalDateTime.of(1999, 12, 31, 23, 59, 59);
        final var expectedTrue = true;
        final var expectedDateNow = LocalDateTime.now();
        final var expectedForgetPassowrd = ForgetPassword.restore(
                expectedForgetPasswordId,
                expectedAccountId,
                expectedHashCode,
                expectedExpirationDate,
                expectedTrue,
                expectedTrue,
                expectedDateNow,
                expectedDateNow
        );
        when(forgetPasswordRepository.findHashConfirmedFalse(any(), any())).thenReturn(expectedForgetPassowrd);

        final var exception = assertThrows(InvalidHashForgetPasswordException.class, () -> confirmHashUseCase.execute(expectedHashCode, expectedAccountId));

        assertNotNull(exception, MESSAGE_NOT_NULL);
        assertEquals(expectedMessageError, exception.getMessage(), MESSAGE_TO_EQUAL);
        verify(forgetPasswordRepository, times(1)).findHashConfirmedFalse(any(), any());
        verify(forgetPasswordRepository, times(0)).save(any());
    }
}
