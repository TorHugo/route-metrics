package mock;

import com.dev.torhugo.domain.entity.Account;
import com.dev.torhugo.domain.entity.Position;
import com.dev.torhugo.domain.entity.Route;

import java.util.UUID;

public class DefaultMockIT {

    public static Account createAccount(){
        return Account.create(
                "account name",
                "example@email.com",
                "password@"
        );
    }

    public static Route createRoute(final UUID accountId){
        return Route.create(
                accountId,
                -25.346860675274446,
                -49.17790747806736
        );
    }

    public static Position createPosition(final UUID routeId){
        return Position.create(
                routeId,
                -25.346860675274446,
                -49.17790747806736
        );
    }
}
