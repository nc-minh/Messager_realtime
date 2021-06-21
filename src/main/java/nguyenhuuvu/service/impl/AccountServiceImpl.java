package nguyenhuuvu.service.impl;

import lombok.AllArgsConstructor;
import nguyenhuuvu.exception.DuplicateEmailException;
import nguyenhuuvu.exception.GenericUsernameException;
import nguyenhuuvu.model.Account;
import nguyenhuuvu.model.Verify;
import nguyenhuuvu.repository.AccountRepository;
import nguyenhuuvu.service.AccountService;
import nguyenhuuvu.utils.AccountUtil;
import nguyenhuuvu.utils.Constant;
import nguyenhuuvu.utils.DateTimeUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    final AccountRepository accountRepository;

    @Transactional
    public Account signUpAccount(Account account) {
        Account accountCheck = accountRepository.findAccountByEmail(account.getEmail());
        if (accountCheck != null) {
            throw new DuplicateEmailException("Email is not exist!");
        }

        // create username random and check
        String username = null;
        boolean isError = true;
        for (int i = 1; i <= 10; i++) {
            username = AccountUtil.createUsername(account.getFullname());
            Account temp = accountRepository.findAccountByUsername(username);
            if (temp == null) {
                isError = false;
                break;
            }
        }

        if (isError)
            throw new GenericUsernameException();

        account.setEnabled(false);
        account.setUsername(username);

        String token = AccountUtil.generateToken();
        String code = AccountUtil.generateCode().toString();
        Verify verify = new Verify(token, code, DateTimeUtil.calculateExpiryDate(Constant.TIME_VERIFY_SIGNUP), false);
        account.setVerify(verify);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        return accountRepository.save(account);
    }


    public Account findAccountByEmail(String email) {
        return accountRepository.findAccountByEmail(email);
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }
}
