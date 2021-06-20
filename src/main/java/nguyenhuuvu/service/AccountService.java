package nguyenhuuvu.service;

import nguyenhuuvu.model.Account;

public interface AccountService {
    Account signUpAccount(Account account);

    Account findAccountByEmail(String email);
}
