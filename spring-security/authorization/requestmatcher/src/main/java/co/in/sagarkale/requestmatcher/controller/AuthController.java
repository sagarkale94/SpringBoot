package co.in.sagarkale.requestmatcher.controller;

import co.in.sagarkale.requestmatcher.entity.AuthRequest;
import co.in.sagarkale.requestmatcher.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public String authenticateUser(@RequestBody AuthRequest authRequest){
        return authService.authenticate(authRequest);
    }
}
