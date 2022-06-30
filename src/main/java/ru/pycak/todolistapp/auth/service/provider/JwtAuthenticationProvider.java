package ru.pycak.todolistapp.auth.service.provider;

import org.springframework.security.core.Authentication;
import ru.pycak.todolistapp.auth.exception.JwtAuthenticationException;

import javax.servlet.http.HttpServletRequest;

public interface JwtAuthenticationProvider {

    /**
     * @param request http request which contains Authorization header with Bearer token
     * @return Authentication token
     * @throws JwtAuthenticationException if request Authorization header is absent,
     * or the Bearer JWT token is expired or invalid
     */
    Authentication getAuthentication(HttpServletRequest request) throws JwtAuthenticationException;
}
