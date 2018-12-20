package me.mocha.appjam.controller;

import me.mocha.appjam.payload.request.auth.AuthRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping
    public void auth(@RequestBody AuthRequest request) {

    }

}
