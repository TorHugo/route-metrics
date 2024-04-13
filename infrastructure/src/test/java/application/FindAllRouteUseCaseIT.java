package application;

import annotation.IntegrationTest;
import com.dev.torhugo.application.ports.repository.AccountRepository;
import com.dev.torhugo.application.ports.repository.RouteRepository;
import com.dev.torhugo.application.usecase.FindAllRouteUseCase;
import com.dev.torhugo.domain.exception.RepositoryException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import static mock.DefaultMockIT.createAccount;
import static mock.DefaultMockIT.createRoute;
import static org.junit.jupiter.api.Assertions.*;
import static util.MessageUtils.*;

@IntegrationTest
class FindAllRouteUseCaseIT {

    @Autowired
    private FindAllRouteUseCase findAllRouteUseCase;

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    @Sql(scripts = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void shouldFindAllRoutesWithSuccess() {
        final var account = createAccount();
        final var route = createRoute(account.getAccountId());
        accountRepository.save(account);
        routeRepository.save(route);

        final var routes = findAllRouteUseCase.execute(account.getEmail());

        assertFalse(routes.isEmpty(), MESSAGE_FALSE);
    }

    @Test
    @Sql(scripts = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void shouldEmptyReturnsWhenNotSavedRoute(){
        final var account = createAccount();
        accountRepository.save(account);

        final var routes = findAllRouteUseCase.execute(account.getEmail());

        assertTrue(routes.isEmpty(), MESSAGE_TRUE);
    }

    @Test
    @Sql(scripts = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void shouldThrowExceptionWhenAccountNotFound(){
        final var expectedException = "Account not found!";

        final var exception = assertThrows(RepositoryException.class, () -> findAllRouteUseCase.execute("email"));
        assertNotNull(exception);
        assertEquals(expectedException, exception.getMessage(), MESSAGE_TO_EQUAL);
    }
}
