package nguyenhuuvu.configuration;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import nguyenhuuvu.exception.JwtTokenException;
import nguyenhuuvu.service.impl.JwtUserDetailsService;
import nguyenhuuvu.utils.JwtTokenUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
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
        boolean expire = true;
        String jwtToken = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
                expire = jwtTokenUtil.isTokenExpired(jwtToken);
            } catch (Exception e) {
                logger.warn("Unable to get JWT Token!");
            }
        }

        if (username != null && !expire && SecurityContextHolder.getContext().getAuthentication() == null) {
            Authentication authentication = jwtTokenUtil.getAuthentication(jwtToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
