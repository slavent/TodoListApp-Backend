package ru.pycak.todolistapp.auth.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import ru.pycak.todolistapp.auth.dto.AuthRequestDTO;
import ru.pycak.todolistapp.auth.dto.AuthResponseDTO;
import ru.pycak.todolistapp.user.dto.CreateUserDTO;
import ru.pycak.todolistapp.user.dto.UserDTO;
import ru.pycak.todolistapp.user.exception.UserDoesNotExistException;
import ru.pycak.todolistapp.user.model.UserModel;
import ru.pycak.todolistapp.auth.service.decoder.JwtTokenDecoder;
import ru.pycak.todolistapp.auth.service.provider.JwtTokenProvider;
import ru.pycak.todolistapp.user.service.UserService;

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
