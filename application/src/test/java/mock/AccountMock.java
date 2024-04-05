package mock;

import com.dev.torhugo.domain.entity.Account;
import com.dev.torhugo.dtos.UcBasicAccountDTO;

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

    public static UcBasicAccountDTO createBasicAccount(){
        final var account = createAccount();
        return new UcBasicAccountDTO(
                account.getAccountId(),
                account.getName(),
                account.getEmail(),
                account.isActive(),
                account.isAdmin(),
                account.getLastAccess(),
                account.getCreatedAt(),
                account.getUpdatedAt()
        );
    }
}
