package nguyenhuuvu.repository;

import nguyenhuuvu.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, String> {
    Account findAccountByUsername(String username);
    Account findAccountByEmail(String username);
}
