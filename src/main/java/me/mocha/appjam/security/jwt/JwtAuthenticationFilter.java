package me.mocha.appjam.security.jwt;

import me.mocha.appjam.exception.NotFoundException;
import me.mocha.appjam.model.repository.UserRepository;
import me.mocha.appjam.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider tokenProvider;
    private final UserDetailsServiceImpl userDetailsService;
    private final UserRepository userRepository;

    @Autowired
    public JwtAuthenticationFilter(JwtProvider tokenProvider, UserDetailsServiceImpl userDetialsService, UserRepository userRepository) {
        this.tokenProvider = tokenProvider;
        this.userDetailsService = userDetialsService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        try {
            String header = request.getHeader("Authorization");
            String token = null;
            if (StringUtils.hasText(header) && header.startsWith("Bearer")) token = header.replaceFirst("Bearer", "").trim();
            if (StringUtils.hasText(token) && tokenProvider.validToken(token, JwtType.ACCESS)) {
                String username = tokenProvider.getUsernameFromJwt(token);
                if (!userRepository.existsById(username)) throw new NotFoundException("cannot find user");
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            //TODO logging
        }
        filterChain.doFilter(request, response);
    }
}
