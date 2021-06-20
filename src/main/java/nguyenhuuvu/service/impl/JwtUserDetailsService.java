package nguyenhuuvu.service.impl;

import lombok.AllArgsConstructor;
import nguyenhuuvu.exception.AccountNotFoundException;
import nguyenhuuvu.model.Account;
import nguyenhuuvu.repository.AccountRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@AllArgsConstructor
@Service
public class JwtUserDetailsService implements UserDetailsService {
    final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        // login with email or username
        Account account = accountRepository.findAccountByEmail(s);
        if (account == null)
            account = accountRepository.findAccountByUsername(s);
        if (account == null)
            throw new AccountNotFoundException("Login false!");
        return new User(account.getUsername(), account.getPassword(), new ArrayList<>());
    }
}
