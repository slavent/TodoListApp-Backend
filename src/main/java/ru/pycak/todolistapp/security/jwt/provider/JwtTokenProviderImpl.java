package ru.pycak.todolistapp.security.jwt.provider;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProviderImpl implements JwtTokenProvider {

    private final String secret;
    private final Long accessExpirationMilliseconds;
    private final Long refreshExpirationMilliseconds;

    public JwtTokenProviderImpl(
            @Value("${jwt.token.secret}") String secret,
            @Value("${jwt.token.expiration.access}") Long accessExpirationMilliseconds,
            @Value("${jwt.token.expiration.refresh}") Long refreshExpirationMilliseconds) {
        this.secret = secret;
        this.accessExpirationMilliseconds = accessExpirationMilliseconds;
        this.refreshExpirationMilliseconds = refreshExpirationMilliseconds;
    }

    @Override
    public String getAccessToken(String username, List<String> authorities) {
        Algorithm algorithm = Algorithm.HMAC256(secret.getBytes(StandardCharsets.UTF_8));
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + accessExpirationMilliseconds);

        return JWT.create()
                .withSubject(username)
                .withIssuedAt(now)
                .withExpiresAt(expireDate)
                .withClaim("roles", authorities)
                .sign(algorithm);
    }

    @Override
    public String getRefreshToken(String username) {
        Algorithm algorithm = Algorithm.HMAC256(secret.getBytes(StandardCharsets.UTF_8));
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + refreshExpirationMilliseconds);

        return JWT.create()
                .withSubject(username)
                .withIssuedAt(now)
                .withExpiresAt(expireDate)
                .sign(algorithm);
    }
}
