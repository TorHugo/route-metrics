package usecase;

import com.dev.torhugo.application.ports.repository.AccountRepository;
import com.dev.torhugo.application.ports.repository.RouteRepository;
import com.dev.torhugo.application.usecase.ConfirmRouteUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import util.MessageUtil;

import static mock.UseCaseMock.createAccount;
import static mock.UseCaseMock.createRoute;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ConfirmRouteUseCaseTest extends MessageUtil {

    @InjectMocks
    ConfirmRouteUseCase confirmRouteUseCase;

    @Mock
    AccountRepository accountRepository;

    @Mock
    RouteRepository routeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldConfirmRouteWithSuccess(){
        final var account = createAccount();
        final var route = createRoute();
        when(accountRepository.findByEmailWithThrow(any())).thenReturn(account);
        when(routeRepository.findByIdAndAccount(any(), any())).thenReturn(route);
        doNothing().when(routeRepository).save(any());

        confirmRouteUseCase.execute(route.getRouteId(), account.getEmail());

        verify(accountRepository, times(1)).findByEmailWithThrow(any());
        verify(routeRepository, times(1)).findByIdAndAccount(any(), any());
        verify(routeRepository, times(1)).save(any());
    }
}
