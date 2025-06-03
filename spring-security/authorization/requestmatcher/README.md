# Autherization Using Request Matcher

### Config to bypass and autherize incoming request
Code snippet from ```SecurityConfig.java```

```declarative
 @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers("/h2-console/**", "/favicon.ico", "/login")
                                .permitAll()
                                .requestMatchers(HttpMethod.GET,"/invoice").hasAnyRole(Role.USER.name(), Role.ADMIN.name())
                                .requestMatchers(HttpMethod.POST,"/invoice").hasRole(Role.ADMIN.name())
                                .requestMatchers(HttpMethod.DELETE,"/invoice").hasAuthority(Permissions.DELETE_PERMISSION.name())
                                .anyRequest()
                                .authenticated()
                )
                .headers(headers-> headers.frameOptions(frame-> frame.sameOrigin()))
                // We want to inspect the request headers for a token before
                // any other form of authentication happens — especially before
                // the username/password filter, which is for login.
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
```

### Drawback
Not scalable approach when multiple roles and permission exists in production ready app

Hence, "Method Security" is preferred approach

### Authorization Flow

![Image](https://github.com/user-attachments/assets/b78630c5-b4b1-4d7e-9622-ba23c27233c6)