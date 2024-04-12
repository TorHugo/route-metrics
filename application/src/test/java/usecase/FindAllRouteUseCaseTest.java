package usecase;

import com.dev.torhugo.application.ports.repository.AccountRepository;
import com.dev.torhugo.application.ports.repository.RouteRepository;
import com.dev.torhugo.application.usecase.FindAllRouteUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import util.MessageUtil;

import java.util.List;

import static mock.UseCaseMock.createAccount;
import static mock.UseCaseMock.createRoute;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class FindAllRouteUseCaseTest extends MessageUtil {
    @InjectMocks
    FindAllRouteUseCase useCase;
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
        // Given
        final var route = createRoute();
        when(routeRepository.findAllByAccount(any())).thenReturn(List.of(route));
        when(accountRepository.findByEmailWithThrow(any())).thenReturn(createAccount());

        // When
        final var result = useCase.execute("usr_email@example.com");

        // Then
        assertNotNull(result, MESSAGE_NOT_NULL);
        assertEquals(route.getRouteId(), result.get(0).routeId(), MESSAGE_TO_EQUAL);
        assertEquals(route.getAccountId(), result.get(0).accountId(), MESSAGE_TO_EQUAL);
        assertEquals(route.getStatus(), result.get(0).status(), MESSAGE_TO_EQUAL);
        assertEquals(route.isActive(), result.get(0).active(), MESSAGE_TO_EQUAL);
        assertEquals(route.getStartCoordinate().latitude(), result.get(0).startCoordinate().latitude(), MESSAGE_TO_EQUAL);
        assertEquals(route.getStartCoordinate().longitude(), result.get(0).startCoordinate().longitude(), MESSAGE_TO_EQUAL);
        assertEquals(route.getCreatedAt(), result.get(0).createdAt(), MESSAGE_TO_EQUAL);
        assertEquals(route.getUpdatedAt(), result.get(0).updatedAt(), MESSAGE_TO_EQUAL);
        verify(routeRepository, times(1)).findAllByAccount(any());
        verify(accountRepository, times(1)).findByEmailWithThrow(any());
    }
}
