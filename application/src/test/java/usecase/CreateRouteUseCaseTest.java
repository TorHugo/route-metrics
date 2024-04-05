package usecase;

import com.dev.torhugo.domain.error.exception.RepositoryNotFoundError;
import com.dev.torhugo.dtos.UcRouteDTO;
import com.dev.torhugo.ports.repository.AccountRepository;
import com.dev.torhugo.ports.repository.RouteRepository;
import com.dev.torhugo.usecase.CreateRouteUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import util.MessageUtil;

import java.util.UUID;

import static mock.AccountMock.createAccount;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class CreateRouteUseCaseTest extends MessageUtil {
    @InjectMocks
    CreateRouteUseCase useCase;
    @Mock
    RouteRepository routeRepository;
    @Mock
    AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldExecuteUseCaseWithSuccess(){
        // Given
        final var expectedAccountId = UUID.randomUUID();
        final var expectedLatitude = Math.random();
        final var expectedLongitude = Math.random();
        final var input = new UcRouteDTO(expectedAccountId, expectedLatitude, expectedLongitude);
        when(accountRepository.findByAccountId(any())).thenReturn(createAccount());
        doNothing().when(routeRepository).save(any());

        // When
        final var result = useCase.execute(input);

        // Then
        assertNotNull(result, MESSAGE_NOT_NULL);
        verify(accountRepository, times(1)).findByAccountId(any());
        verify(routeRepository, times(1)).save(any());
    }

    @Test
    void shouldThrowExceptionWhenAccountNotFound(){
        // Given
        final var expectedAccountId = UUID.randomUUID();
        final var expectedLatitude = Math.random();
        final var expectedLongitude = Math.random();
        final var input = new UcRouteDTO(expectedAccountId, expectedLatitude, expectedLongitude);
        when(accountRepository.findByAccountId(any()))
                .thenThrow(new RepositoryNotFoundError(ACCOUNT_NOT_FOUND));

        // When
        final var result = assertThrows(RepositoryNotFoundError.class, () -> useCase.execute(input));

        // Then
        assertEquals(ACCOUNT_NOT_FOUND, result.getMessage(), MESSAGE_TO_EQUAL);
        verify(accountRepository, times(1)).findByAccountId(any());
        verify(routeRepository, times(0)).save(any());
    }
}
