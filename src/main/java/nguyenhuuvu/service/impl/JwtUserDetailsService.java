package nguyenhuuvu.service.impl;

import lombok.AllArgsConstructor;
import nguyenhuuvu.exception.AccountHandleException;
import nguyenhuuvu.model.Account;
import nguyenhuuvu.repository.AccountRepository;
import org.springframework.security.core.AuthenticationException;
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
        Account account = accountRepository.findAccountByUsernameOrEmail(s, s);
        if (account == null)
            throw new UsernameNotFoundException("Login false: " + s);
        return new User(account.getUsername(), account.getPassword(), account.isEnabled(), true, true, true, new ArrayList<>());
    }
}
