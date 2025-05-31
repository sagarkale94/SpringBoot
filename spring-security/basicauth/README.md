# Basic Authentication

### How Basic Authentication works
Basic Authentication is a simple authentication scheme built into the HTTP protocol. It allows a client (like a browser or API consumer) to send a username and password with each request to authenticate with a server.

The client resends the request which includes an Authorization header:

```declarative
Authorization: Basic dXNlcm5hbWU6cGFzc3dvcmQ=
```

dXNlcm5hbWU6cGFzc3dvcmQ= is the Base64 encoding of username:password.

### Security Concerns

* Base64 is not encryption—it’s easy to decode.
* Basic Auth should always be used over HTTPS to prevent credentials from being intercepted.
* The credentials are sent on every request — no session or token.

### Enable Basic Authentication in SpringBoot Application

```declarative
package co.in.sagarkale.basicauth.config;

import co.in.sagarkale.basicauth.service.CustomUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers("/h2-console/**", "/favicon.ico")
                                .permitAll() # ✅ Specified URLs will be bypassed
                                .anyRequest()
                                .authenticated( )# ✅ Rest all URLS will be authenticated
                )
                .headers(headers -> headers
                        .frameOptions(frame -> frame.sameOrigin())
                )
                .httpBasic(Customizer.withDefaults()); # ✅ Enabled basic authentication with default config
        return http.build();
    }

    # ✅ Custom user details service as we want to verify
    # user from database (here h2 used)
    # if we do not configure this then username password 
    # in applicaton.properties will be used 
    # which is not good to use in production
    @Bean
    public UserDetailsService userDetailsService(){
        return new CustomUserDetailService(); 
    }

    # ✅ Custom password encoder
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    # ✅ Using authentication manger provider use 
    # custom userdetail service and password encoder
    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService,
                                                       PasswordEncoder passwordEncoder){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(daoAuthenticationProvider);
    }

}
```

### Create default users in DB

In order to test this basic auth, we now need to create some user in db which is achieved using ```AdminUserInitializer```

```declarative
package co.in.sagarkale.basicauth.service;


import co.in.sagarkale.basicauth.entity.Users;
import co.in.sagarkale.basicauth.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminUserInitializer implements CommandLineRunner {
    @Autowired
    private UserDetailsRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByUsername("admin").isEmpty()) {
            Users admin = new Users();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin1234")); // Securely store password


            userRepository.save(admin);
            System.out.println("Default admin user created!");
        }
        if (userRepository.findByUsername("user").isEmpty()) {
            Users admin = new Users();
            admin.setUsername("user");
            admin.setPassword(passwordEncoder.encode("user1234")); // Securely store password


            userRepository.save(admin);
            System.out.println("Default admin user created!");
        }
    }

}
```


### Without Database (🛡️ Not Secured Way)

If you just want it to be through hardcoded username password then configure application.properties as follows

```declarative
# Spring Security default user
spring.security.user.name=admin
spring.security.user.password=secret
spring.security.user.roles=USER
```
