package co.in.sagarkale.jwt.service;

import co.in.sagarkale.jwt.entity.AuthRequest;
import co.in.sagarkale.jwt.entity.Users;
import co.in.sagarkale.jwt.repository.UserDetailsRepository;
import co.in.sagarkale.jwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Long register(AuthRequest authRequest){
        try {
            Users users = new Users();
            users.setUsername(authRequest.getUsername());
            String encodedPassword = passwordEncoder.encode(authRequest.getPassword());
            users.setPassword(encodedPassword);

            Users createdUser = userDetailsRepository.save(users);
            return createdUser.getId();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

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
