package co.in.sagarkale.methodsecurity.service;

import co.in.sagarkale.methodsecurity.entity.AuthRequest;
import co.in.sagarkale.methodsecurity.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
