package nguyenhuuvu.repository;

import nguyenhuuvu.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, String> {
    Account findAccountByUsername(String username);
    Account findAccountByEmail(String username);
    Account findAccountByUsernameOrEmail(String username, String email);
    Account findAccountByVerify_Token(String token);
    Account findAccountByEmailAndVerify_Code(String email, String code);
}
