package nguyenhuuvu.api;

import lombok.AllArgsConstructor;
import nguyenhuuvu.model.Account;
import nguyenhuuvu.repository.AccountRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TestControler {
    final AccountRepository accountRepository;

    @RequestMapping("/")
    public Account fetchAccount()
    {
        return accountRepository.findAccountByEmail("s2huuvuno1@gmail.com");
    }
}
