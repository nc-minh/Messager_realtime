package nguyenhuuvu.api;

import lombok.AllArgsConstructor;
import nguyenhuuvu.exception.AccountHandleException;
import nguyenhuuvu.model.JwtRequest;
import nguyenhuuvu.service.impl.JwtUserDetailsService;
import nguyenhuuvu.utils.JwtTokenUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
    final AuthenticationManager authenticationManager;
    final JwtTokenUtil jwtTokenUtil;
    final JwtUserDetailsService jwtUserDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest jwtRequest){
        authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    private void authenticate(String username, String password) throws AccountHandleException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new AccountHandleException("USER_DISABLED");
        } catch (BadCredentialsException e) {
            throw new AccountHandleException("INVALID_CREDENTIALS");
        }
    }
}
