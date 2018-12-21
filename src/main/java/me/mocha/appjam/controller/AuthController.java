package me.mocha.appjam.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import me.mocha.appjam.exception.NotFoundException;
import me.mocha.appjam.exception.UnauthorizedException;
import me.mocha.appjam.model.entiity.User;
import me.mocha.appjam.model.repository.UserRepository;
import me.mocha.appjam.payload.request.auth.AuthRequest;
import me.mocha.appjam.payload.response.AuthResponse;
import me.mocha.appjam.security.jwt.JwtProvider;
import me.mocha.appjam.security.jwt.JwtType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, JwtProvider jwtProvider, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public AuthResponse auth(@Valid @RequestBody AuthRequest request) {
        User user = userRepository.findById(request.getUsername()).orElseThrow(() -> new NotFoundException("cannot find user"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new UnauthorizedException("Incorrect password");
        }
        String access = jwtProvider.generateToken(user.getUsername(), JwtType.ACCESS);
        String refresh = jwtProvider.generateToken(user.getUsername(), JwtType.REFRESH);
        return new AuthResponse(access, refresh);
    }

}