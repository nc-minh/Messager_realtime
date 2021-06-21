package nguyenhuuvu.service;

import nguyenhuuvu.model.Account;

import java.util.List;

public interface AccountService {
    Account signUpAccount(Account account);

    Account findAccountByEmail(String email);

    List<Account> findAll();
}
