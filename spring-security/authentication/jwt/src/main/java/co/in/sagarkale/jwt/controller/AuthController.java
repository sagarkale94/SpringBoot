package co.in.sagarkale.jwt.controller;

import co.in.sagarkale.jwt.entity.AuthRequest;
import co.in.sagarkale.jwt.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/signup")
    public Long registerUser(@RequestBody AuthRequest authRequest){
        return authService.register(authRequest);
    }

    @PostMapping("/signin")
    public Object authenticateUser(@RequestBody AuthRequest authRequest){
        return authService.authenticate(authRequest);
    }

}
