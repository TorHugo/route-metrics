package usecase;

import com.dev.torhugo.application.ports.repository.PositionRepository;
import com.dev.torhugo.domain.exception.InvalidArgumentException;
import com.dev.torhugo.domain.exception.RepositoryException;
import com.dev.torhugo.application.dto.UcRouteDTO;
import com.dev.torhugo.application.ports.repository.AccountRepository;
import com.dev.torhugo.application.ports.repository.RouteRepository;
import com.dev.torhugo.application.usecase.RequestRouteUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import util.MessageUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static mock.UseCaseMock.createAccount;
import static mock.UseCaseMock.createRoute;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class CreateRouteUseCaseTest extends MessageUtil {
    @InjectMocks
    RequestRouteUseCase useCase;
    @Mock
    RouteRepository routeRepository;
    @Mock
    AccountRepository accountRepository;
    @Mock
    PositionRepository positionRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateRouteWithIsValidParameters(){
        // Given
        final var expectedAccountId = UUID.randomUUID();
        final var expectedLatitude = Math.random();
        final var expectedLongitude = Math.random();
        final var input = new UcRouteDTO(expectedAccountId, expectedLatitude, expectedLongitude);
        when(accountRepository.findByAccountId(any())).thenReturn(createAccount());
        when(routeRepository.findAllByAccountAndStatus(any(), any())).thenReturn(new ArrayList<>());
        doNothing().when(routeRepository).save(any());
        doNothing().when(positionRepository).save(any());
        // When
        final var result = useCase.execute(input);

        // Then
        assertNotNull(result, MESSAGE_NOT_NULL);
        verify(accountRepository, times(1)).findByAccountId(any());
        verify(routeRepository, times(1)).findAllByAccountAndStatus(any(), any());
        verify(routeRepository, times(1)).save(any());
    }

    @Test
    void shouldThrowExceptionWhenAccountNotFound(){
        // Given
        final var expectedAccountId = UUID.randomUUID();
        final var expectedLatitude = Math.random();
        final var expectedLongitude = Math.random();
        final var input = new UcRouteDTO(expectedAccountId, expectedLatitude, expectedLongitude);
        when(accountRepository.findByAccountId(any())).thenThrow(new RepositoryException(ACCOUNT_NOT_FOUND));

        // When
        final var result = assertThrows(RepositoryException.class, () -> useCase.execute(input));

        // Then
        assertEquals(ACCOUNT_NOT_FOUND, result.getMessage(), MESSAGE_TO_EQUAL);
        verify(accountRepository, times(1)).findByAccountId(any());
        verify(routeRepository, times(0)).findAllByAccountAndStatus(any(), any());
        verify(routeRepository, times(0)).save(any());
        verify(positionRepository, times(0)).save(any());
    }

    @Test
    void shouldThrowExceptionWhenRouteIsPending(){
        // Given
        final var expectedAccountId = UUID.randomUUID();
        final var expectedLatitude = Math.random();
        final var expectedLongitude = Math.random();
        final var input = new UcRouteDTO(expectedAccountId, expectedLatitude, expectedLongitude);
        when(accountRepository.findByAccountId(any())).thenReturn(createAccount());
        when(routeRepository.findAllByAccountAndStatus(any(), any())).thenReturn(List.of(createRoute()));

        // When
        final var result = assertThrows(InvalidArgumentException.class, () -> useCase.execute(input));

        // Then
        assertEquals(HAS_PEINDING_ROUTE, result.getMessage(), MESSAGE_TO_EQUAL);
        verify(accountRepository, times(1)).findByAccountId(any());
        verify(routeRepository, times(1)).findAllByAccountAndStatus(any(), any());
        verify(routeRepository, times(0)).save(any());
        verify(positionRepository, times(0)).save(any());
    }
}
