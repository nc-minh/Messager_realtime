package nguyenhuuvu.repository;

import nguyenhuuvu.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public interface AccountRepository extends MongoRepository<Account, String> {
    Account findAccountByUsername(String username);
    Account findAccountByEmail(String username);
    Account findAccountByUsernameOrEmail(String username, String email);
}
