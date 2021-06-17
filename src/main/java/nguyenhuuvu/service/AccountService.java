package nguyenhuuvu.service;

import lombok.AllArgsConstructor;
import nguyenhuuvu.exception.DuplicateEmailException;
import nguyenhuuvu.model.Account;
import nguyenhuuvu.model.VerifyToken;
import nguyenhuuvu.repository.AccountRepository;
import nguyenhuuvu.utils.AccountUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Calendar;


@Service
@AllArgsConstructor
public class AccountService {
    final AccountRepository accountRepository;

    @Transactional
    public void signUpAccount(Account account) {
        Account accountCheck = accountRepository.findAccountByEmail(account.getEmail());
        if (accountCheck != null)
        {
            throw new DuplicateEmailException("Email đã tồn tại");
        }
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

        String token = AccountUtil.generateToken();
        VerifyToken verifyToken = new VerifyToken();
        verifyToken.setId(token);
        verifyToken.setToken(token);
        verifyToken.setTimeExpire(calculateExpiryDate(60*24));
        account.setVerifyToken(verifyToken);
        accountRepository.save(account);
    }

    public Timestamp calculateExpiryDate(int expiryTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, expiryTime);
        return new Timestamp(calendar.getTime().getTime());
    }
}
