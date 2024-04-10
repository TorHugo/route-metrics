package mock;

import com.dev.torhugo.domain.entity.Account;
import com.dev.torhugo.application.dto.UcBasicAccountDTO;

public class AccountMock {

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
}
