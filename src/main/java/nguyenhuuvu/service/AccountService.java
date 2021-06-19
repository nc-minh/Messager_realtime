package nguyenhuuvu.service;

import lombok.AllArgsConstructor;
import nguyenhuuvu.exception.DuplicateEmailException;
import nguyenhuuvu.exception.GenericUsernameException;
import nguyenhuuvu.model.Account;
import nguyenhuuvu.model.VerifyToken;
import nguyenhuuvu.repository.AccountRepository;
import nguyenhuuvu.utils.AccountUtil;
import nguyenhuuvu.utils.Constant;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;


@Service
@AllArgsConstructor
public class AccountService {
    final AccountRepository accountRepository;

    @Transactional
    public Account signUpAccount(Account account) {
        Account accountCheck = accountRepository.findAccountByEmail(account.getEmail());
        if (accountCheck != null)
        {
            throw new DuplicateEmailException("Email đã tồn tại");
        }

        // create username random and check
        String username = null;
        boolean isError = true;
        for (int i = 1; i <= 10; i++)
        {
            username = AccountUtil.createUsername(account.getFullname());
            Account temp = accountRepository.findAccountByUsername(username);
            if (temp == null)
            {
                isError = false;
                break;
            }
        }

        if (isError)
            throw new GenericUsernameException();

        account.setEnabled(false);
        account.setUsername(username);

        String token = AccountUtil.generateToken();
        int code = AccountUtil.generateCode();
        VerifyToken verifyToken = new VerifyToken(token, code, calculateExpiryDate(Constant.TIME_VERIFY_SIGNUP), false);
        account.setVerifyToken(verifyToken);
        return accountRepository.save(account);
    }

    public Date calculateExpiryDate(int expiryTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, expiryTime);
        return calendar.getTime();
    }

    public Account findAccountByUsernameOrEmail(String q) {
        return accountRepository.findAccountByUsernameOrEmail(q, q);
    }
}
