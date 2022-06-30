package ru.pycak.todolistapp.auth.service.decoder;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

public interface JwtTokenDecoder {

    /**
     * @param token JWT token
     * @return decoded information about the token
     * @throws JWTVerificationException if given token is expired or invalid
     */
    DecodedJWT decode(String token) throws JWTVerificationException;
}
