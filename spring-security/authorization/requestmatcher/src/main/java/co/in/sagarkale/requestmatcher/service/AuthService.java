package co.in.sagarkale.requestmatcher.service;

import co.in.sagarkale.requestmatcher.entity.AuthRequest;
import co.in.sagarkale.requestmatcher.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    public String authenticate(AuthRequest authRequest){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUsername(),
                            authRequest.getPassword()
                    )
            );
            return jwtUtil.generateToken(authRequest.getUsername());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
