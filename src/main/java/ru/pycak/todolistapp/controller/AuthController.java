package ru.pycak.todolistapp.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import ru.pycak.todolistapp.dto.AuthRequestDTO;
import ru.pycak.todolistapp.dto.AuthResponseDTO;
import ru.pycak.todolistapp.dto.CreateUserDTO;
import ru.pycak.todolistapp.dto.UserDTO;
import ru.pycak.todolistapp.exception.UserDoesNotExistException;
import ru.pycak.todolistapp.model.UserModel;
import ru.pycak.todolistapp.security.jwt.decoder.JwtTokenDecoder;
import ru.pycak.todolistapp.security.jwt.provider.JwtTokenProvider;
import ru.pycak.todolistapp.service.UserService;

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
        UserModel model = userService.create(createUserDTO);
        return new UserDTO(model);
    }

    @PostMapping("/login")
    public AuthResponseDTO login(
            @RequestBody AuthRequestDTO authRequestDTO
    ) throws AuthenticationException, UserDoesNotExistException {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                authRequestDTO.getLogin(),
                authRequestDTO.getPassword()
        );
        authenticationManager.authenticate(authentication);

        UserModel model = userService.get(authRequestDTO.getLogin());
        return new AuthResponseDTO(
                tokenProvider.getAccessToken(authRequestDTO.getLogin(), model.getRoles()),
                tokenProvider.getRefreshToken(authRequestDTO.getLogin())
        );
    }

    @GetMapping("/refresh")
    public AuthResponseDTO refresh(
            @RequestParam("token") String refreshToken
    ) throws JWTVerificationException, UserDoesNotExistException {
        DecodedJWT decodedJWT = tokenDecoder.decode(refreshToken);
        String email = decodedJWT.getSubject();

        UserModel model = userService.get(email);
        return new AuthResponseDTO(
                tokenProvider.getAccessToken(email, model.getRoles()),
                tokenProvider.getRefreshToken(email)
        );
    }
}
