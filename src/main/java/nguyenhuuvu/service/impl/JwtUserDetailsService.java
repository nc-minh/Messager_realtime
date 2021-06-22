package nguyenhuuvu.service.impl;

import lombok.AllArgsConstructor;
import nguyenhuuvu.entity.UserEntity;
import nguyenhuuvu.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@AllArgsConstructor
@Service
public class JwtUserDetailsService implements UserDetailsService {
    final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        // login with email or username
        UserEntity user = userRepository.findUserEntityByUsernameOrEmail(s, s);
        if (user == null)
            throw new UsernameNotFoundException("Login false: " + s);
        return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, new ArrayList<>());
    }
}
