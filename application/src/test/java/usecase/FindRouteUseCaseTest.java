package usecase;

import com.dev.torhugo.application.ports.repository.AccountRepository;
import com.dev.torhugo.application.ports.repository.RouteRepository;
import com.dev.torhugo.application.usecase.FindRouteUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import util.MessageUtil;

import static mock.UseCaseMock.createAccount;
import static mock.UseCaseMock.createRoute;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class FindRouteUseCaseTest extends MessageUtil {
    @InjectMocks
    FindRouteUseCase useCase;
    @Mock
    RouteRepository routeRepository;
    @Mock
    AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindRouteWithSuccess(){
        // Given=
        final var route = createRoute();
        when(routeRepository.findByIdAndAccount(any(), any())).thenReturn(route);
        when(accountRepository.findByEmailWithThrow(any())).thenReturn(createAccount());

        // When
        final var result = useCase.execute(route.getRouteId(), "usr_email@example.com");

        // Then
        assertNotNull(result, MESSAGE_NOT_NULL);
        assertEquals(route.getRouteId(), result.routeId(), MESSAGE_TO_EQUAL);
        assertEquals(route.getAccountId(), result.accountId(), MESSAGE_TO_EQUAL);
        assertEquals(route.getStatus(), result.status(), MESSAGE_TO_EQUAL);
        assertEquals(route.isActive(), result.active(), MESSAGE_TO_EQUAL);
        assertEquals(route.getStartCoordinate().latitude(), result.startCoordinate().latitude(), MESSAGE_TO_EQUAL);
        assertEquals(route.getStartCoordinate().longitude(), result.startCoordinate().longitude(), MESSAGE_TO_EQUAL);
        assertEquals(route.getCreatedAt(), result.createdAt(), MESSAGE_TO_EQUAL);
        assertEquals(route.getUpdatedAt(), result.updatedAt(), MESSAGE_TO_EQUAL);
        verify(routeRepository, times(1)).findByIdAndAccount(any(), any());
        verify(accountRepository, times(1)).findByEmailWithThrow(any());
    }
}
