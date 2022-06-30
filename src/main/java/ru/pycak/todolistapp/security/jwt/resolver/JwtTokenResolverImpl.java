package ru.pycak.todolistapp.security.jwt.resolver;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Component
public class JwtTokenResolverImpl implements JwtTokenResolver {

    @Override
    public Optional<String> resolveToken(HttpServletRequest request) {
        String keyword = "Bearer ";
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith(keyword)) {
            return Optional.of(authHeader.substring(keyword.length()));
        }
        return Optional.empty();
    }
}
