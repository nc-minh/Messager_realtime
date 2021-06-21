package nguyenhuuvu.api;

import lombok.AllArgsConstructor;
import nguyenhuuvu.exception.AccountHandleException;
import nguyenhuuvu.model.JwtRequest;
import nguyenhuuvu.model.JwtResponse;
import nguyenhuuvu.service.impl.JwtUserDetailsService;
import nguyenhuuvu.utils.JwtTokenUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/accounts")
public class JwtAuthenticationController {
    final AuthenticationManagerBuilder authenticationManagerBuilder;
    final JwtTokenUtil jwtTokenUtil;
    final JwtUserDetailsService jwtUserDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest jwtRequest){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                jwtRequest.getEmail(), jwtRequest.getPassword()
        );
        Authentication authentication = null;
        try {
            authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        } catch (DisabledException e) {
            throw new AccountHandleException("This account is not activated!");
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenUtil.generateToken(authentication);
        return new ResponseEntity<>(new JwtResponse(token), HttpStatus.OK);
    }

}
