package ru.pycak.todolistapp.auth.service.provider;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import ru.pycak.todolistapp.auth.service.decoder.JwtTokenDecoder;
import ru.pycak.todolistapp.auth.exception.JwtAuthenticationException;
import ru.pycak.todolistapp.auth.service.resolver.JwtTokenResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationProviderImpl implements JwtAuthenticationProvider {

    private final JwtTokenResolver tokenResolver;
    private final JwtTokenDecoder tokenDecoder;

    @Override
    public Authentication getAuthentication(HttpServletRequest request) throws JwtAuthenticationException {
        String token = tokenResolver
                .resolveToken(request)
                .orElseThrow(() -> new JwtAuthenticationException("Authentication token not found"));

        DecodedJWT decodedJWT;
        try {
            decodedJWT = tokenDecoder.decode(token);
        } catch (JWTVerificationException e) {
            throw new JwtAuthenticationException(e.getMessage());
        }

        Collection<SimpleGrantedAuthority> authorities;
        try {
            authorities = decodedJWT
                    .getClaim("roles")
                    .asList(String.class)
                    .stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            throw new JwtAuthenticationException("Access token is invalid");
        }

        return new UsernamePasswordAuthenticationToken(
                decodedJWT.getSubject(),
                null,
                authorities
        );
    }
}
