package mock;

import com.dev.torhugo.domain.entity.Account;
import com.dev.torhugo.domain.entity.Position;
import com.dev.torhugo.domain.entity.Route;

import java.util.UUID;

public class UseCaseMock {

    public static Account createAccount(){
        final var expectedName = "Account Test";
        final var expectedEmail = "account.test@dev.com.br";
        final var expectedPassword = "Password@";
        return Account.create(
                expectedName,
                expectedEmail,
                expectedPassword
        );
    }

    public static Route createRoute(){
        final var expectedAccount = UUID.randomUUID();
        final var expectedLatitude = Math.random();
        final var expectedLongitude = Math.random();
        return Route.create(
                expectedAccount,
                expectedLatitude,
                expectedLongitude
        );
    }

    public static Route createRoute(final Double latitude,
                                    final Double longitude){
        final var expectedAccount = UUID.randomUUID();
        return Route.create(
                expectedAccount,
                latitude,
                longitude
        );
    }

    public static Position createPosition(final Double latitude,
                                          final Double longitude){
        final var expectedRoute = UUID.randomUUID();
        return Position.create(
                expectedRoute,
                latitude,
                longitude
        );
    }
}
