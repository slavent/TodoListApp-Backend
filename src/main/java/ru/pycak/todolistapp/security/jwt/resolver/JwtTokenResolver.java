package ru.pycak.todolistapp.security.jwt.resolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public interface JwtTokenResolver {

    /**
     * @param request http request with Authorization header.
     * @return Bearer token string, or Optional.empty(),
     * if Bearer token is not present in the Authorization header.
     */
    public Optional<String> resolveToken(HttpServletRequest request);
}
