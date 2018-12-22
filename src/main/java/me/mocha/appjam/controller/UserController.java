package me.mocha.appjam.controller;

import lombok.extern.slf4j.Slf4j;
import me.mocha.appjam.annotation.CurrentUser;
import me.mocha.appjam.exception.ConflictException;
import me.mocha.appjam.model.entiity.User;
import me.mocha.appjam.model.repository.UserRepository;
import me.mocha.appjam.payload.request.user.CreateUserRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public void createUser(@Valid @RequestBody CreateUserRequest request) throws ConflictException {
        if (userRepository.existsByUsernameOrNameOrStudentId(request.getUsername(), request.getName(), request.getStudentId())) {
            throw new ConflictException("already exists user");
        }
        User user = userRepository.save(User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .studentId(request.getStudentId())
                .role("ROLE_USER")
                .build());
        log.info("create user - {}", user.getUsername());
    }

    @GetMapping
    public User getInfo(@CurrentUser User user) {
        return user;
    }

}
