package ru.pycak.todolistapp.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.pycak.todolistapp.dto.AuthRequestDTO;
import ru.pycak.todolistapp.dto.AuthResponseDTO;
import ru.pycak.todolistapp.dto.CreateUserDTO;
import ru.pycak.todolistapp.dto.UserDTO;
import ru.pycak.todolistapp.security.jwt.decoder.JwtTokenDecoder;
import ru.pycak.todolistapp.security.jwt.provider.JwtTokenProvider;
import ru.pycak.todolistapp.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenProvider tokenProvider;
    private final JwtTokenDecoder tokenDecoder;

    @PostMapping("/register")
    public UserDTO createUser(@RequestBody CreateUserDTO createUserDTO) {
        return userService.create(createUserDTO);
    }

    @PostMapping("/login")
    public AuthResponseDTO login(
            @RequestBody AuthRequestDTO authRequestDTO
    ) throws AuthenticationException {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                authRequestDTO.getLogin(),
                authRequestDTO.getPassword()
        );
        authenticationManager.authenticate(authentication);

        // TODO: Refactor UserService to return UserModel which will have list of roles
        return new AuthResponseDTO(
                tokenProvider.getAccessToken(authRequestDTO.getLogin(), List.of("ROLE_USER")),
                tokenProvider.getRefreshToken(authRequestDTO.getLogin())
        );
    }

    @GetMapping("/refresh")
    public AuthResponseDTO refresh(
            @RequestParam("token") String refreshToken
    ) throws JWTVerificationException, UsernameNotFoundException {
        DecodedJWT decodedJWT = tokenDecoder.decode(refreshToken);
        String email = decodedJWT.getSubject();

        UserDTO userDTO = userService.get(email);
        if (userDTO == null) {
            throw new UsernameNotFoundException("User with email '"+email+"' not found in database");
        }

        // TODO: Refactor UserService to return UserModel which will have list of roles
        return new AuthResponseDTO(
                tokenProvider.getAccessToken(email, List.of("ROLE_USER")),
                tokenProvider.getRefreshToken(email)
        );
    }
}
