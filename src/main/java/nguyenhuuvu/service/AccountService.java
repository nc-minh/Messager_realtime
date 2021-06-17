package nguyenhuuvu.service;

import lombok.AllArgsConstructor;
import nguyenhuuvu.model.Account;
import nguyenhuuvu.model.VerifyToken;
import nguyenhuuvu.repository.AccountRepository;
import nguyenhuuvu.utils.AccountUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AccountService {
    final AccountRepository accountRepository;

    @Transactional
    public void signUpAccount(Account account) {
        String username = AccountUtil.createUsername(account.getFirstname(), account.getLastname());
        Account temp = accountRepository.findAccountByUsername(username);
        if (temp != null) {
            // username has format: fisrtname + lastname + . + number
            int num = 0;
            try {
                String index = temp.getUsername().split(".")[1];
                num = Integer.parseInt(index) + 1;
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                num = 2;
            }
            username += "." + num;
        }
        account.setEnabled(false);
        account.setUsername(username);

        VerifyToken verifyToken = new VerifyToken();
        verifyToken.setToken(AccountUtil.generateToken());
        account.setVerifyToken(verifyToken);
        accountRepository.save(account);
    }
}
