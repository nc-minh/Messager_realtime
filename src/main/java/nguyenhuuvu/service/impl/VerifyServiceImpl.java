package nguyenhuuvu.service.impl;

import lombok.AllArgsConstructor;
import nguyenhuuvu.exception.AccountNotFoundException;
import nguyenhuuvu.model.Account;
import nguyenhuuvu.repository.AccountRepository;
import nguyenhuuvu.service.VerifyService;
import nguyenhuuvu.utils.DateTimeUtil;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VerifyServiceImpl implements VerifyService {
    final AccountRepository accountRepository;

    public boolean verifyToken(String token) {
        Account account = accountRepository.findAccountByVerify_Token(token);
        return verify(account);
    }

    public boolean verifyCode(String email, String code) {
        Account account = accountRepository.findAccountByEmail(email);
        if (account == null)
            throw new AccountNotFoundException("Email is not linked to any accounts");
        if (account.getVerify().getCode().equals(code))
            return verify(account);
        return false;
    }

    private boolean verify(Account account) {
        if (account != null) {
            if (account.getVerify().isUsed())
                return false;
            if (DateTimeUtil.checkTokenExpire(account.getVerify().getTimeExpire())) {
                account.getVerify().setUsed(true);
                accountRepository.save(account);
                return true;
            }
        }
        return false;
    }

}