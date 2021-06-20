package nguyenhuuvu.configuration;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import nguyenhuuvu.exception.JwtTokenException;
import nguyenhuuvu.service.impl.JwtUserDetailsService;
import nguyenhuuvu.utils.JwtTokenUtil;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {
    final JwtTokenUtil jwtTokenUtil;
    final JwtUserDetailsService jwtUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader("Authorization");

        String username = null;
        String jwtToken = null;

        if (request != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                throw new JwtTokenException("Unable to get JWT Token!");
            } catch (ExpiredJwtException e) {
                throw new JwtTokenException("Unable to get JWT Token!");
            }
        }
        else {
            throw new JwtTokenException("Tokens is missing!");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() != null) {
            UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);

        }
    }
}
