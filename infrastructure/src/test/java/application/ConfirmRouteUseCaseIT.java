package application;

import annotation.IntegrationTest;
import com.dev.torhugo.application.ports.repository.AccountRepository;
import com.dev.torhugo.application.ports.repository.RouteRepository;
import com.dev.torhugo.application.usecase.ConfirmRouteUseCase;
import com.dev.torhugo.domain.exception.InvalidArgumentException;
import com.dev.torhugo.domain.exception.RepositoryException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import static mock.DefaultMockIT.createAccount;
import static mock.DefaultMockIT.createRoute;
import static org.junit.jupiter.api.Assertions.*;
import static util.MessageUtils.MESSAGE_NOT_NULL;
import static util.MessageUtils.MESSAGE_TO_EQUAL;

@IntegrationTest
class ConfirmRouteUseCaseIT {

    @Autowired
    private ConfirmRouteUseCase confirmRouteUseCase;

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    @Sql(scripts = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void shouldConfirmRouteWithSuccess(){
        final var expectedStatus = "confirmed";
        final var account = createAccount();
        final var route = createRoute(account.getAccountId());
        accountRepository.save(account);
        routeRepository.save(route);

        confirmRouteUseCase.execute(route.getRouteId(), account.getEmail());
        final var actualRoute = routeRepository.findById(route.getRouteId());

        assertNotNull(actualRoute, MESSAGE_NOT_NULL);
        assertEquals(route.getStartCoordinate().latitude(), actualRoute.getStartCoordinate().latitude(), MESSAGE_TO_EQUAL);
        assertEquals(route.getStartCoordinate().longitude(), actualRoute.getStartCoordinate().longitude(), MESSAGE_TO_EQUAL);
        assertEquals(expectedStatus, actualRoute.getStatus(), MESSAGE_TO_EQUAL);
        assertNotNull(actualRoute.getUpdatedAt(), MESSAGE_NOT_NULL);
    }

    @Test
    @Sql(scripts = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void shouldThrowExceptionWhenAccountNotFound(){
        final var expectedException = "Account not found!";
        final var account = createAccount();
        final var route = createRoute(account.getAccountId());
        routeRepository.save(route);

        final var exception = assertThrows(RepositoryException.class, () -> confirmRouteUseCase.execute(route.getRouteId(), account.getEmail()));
        final var actualRoute = routeRepository.findById(route.getRouteId());

        assertNotNull(exception, MESSAGE_NOT_NULL);
        assertEquals(expectedException, exception.getMessage(), MESSAGE_NOT_NULL);
        assertEquals("requested", actualRoute.getStatus(), MESSAGE_TO_EQUAL);
    }

    @Test
    @Sql(scripts = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void shouldThrowExceptionWhenRouteNotFound(){
        final var expectedException = "Route not found!";
        final var account = createAccount();
        final var route = createRoute(account.getAccountId());
        accountRepository.save(account);

        final var exception = assertThrows(RepositoryException.class, () -> confirmRouteUseCase.execute(route.getRouteId(), account.getEmail()));

        assertNotNull(exception, MESSAGE_NOT_NULL);
        assertEquals(expectedException, exception.getMessage(), MESSAGE_NOT_NULL);
    }

    @Test
    @Sql(scripts = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void shouldThrowExceptionWhenInvalidStatus(){
        final var expectedException = "Invalid status! Expected status: requested.";
        final var expectedStatus = "confirmed";
        final var account = createAccount();
        final var route = createRoute(account.getAccountId());
        accountRepository.save(account);
        routeRepository.save(route);

        confirmRouteUseCase.execute(route.getRouteId(), account.getEmail());
        final var exception = assertThrows(InvalidArgumentException.class, () -> confirmRouteUseCase.execute(route.getRouteId(), account.getEmail()));
        final var actualRoute = routeRepository.findById(route.getRouteId());

        assertNotNull(exception, MESSAGE_NOT_NULL);
        assertEquals(expectedException, exception.getMessage(), MESSAGE_TO_EQUAL);
        assertNotNull(actualRoute, MESSAGE_NOT_NULL);
        assertEquals(route.getStartCoordinate().latitude(), actualRoute.getStartCoordinate().latitude(), MESSAGE_TO_EQUAL);
        assertEquals(route.getStartCoordinate().longitude(), actualRoute.getStartCoordinate().longitude(), MESSAGE_TO_EQUAL);
        assertEquals(expectedStatus, actualRoute.getStatus(), MESSAGE_TO_EQUAL);
        assertNotNull(actualRoute.getUpdatedAt(), MESSAGE_NOT_NULL);
    }
}
