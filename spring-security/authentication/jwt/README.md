# JWT Authentication

## Overall Architecture of Spring security
![Image](https://github.com/user-attachments/assets/8b4af5f5-ee7b-4cfc-9d74-d82c32f3a830)

Spring Security 6 is a powerful and customizable authentication and access control framework for Java applications. This document provides a high-level overview of its core architectural components and their responsibilities.

## 🔐 Core Components

### 1. AuthenticationManager
- **Responsibility**: Manages the authentication process.
- **Function**: Delegates to one or more `AuthenticationProvider`s to verify credentials.

### 2. AuthenticationProvider
- **Responsibility**: Performs the actual authentication.
- **Function**: Validates credentials against a user store (e.g., database, LDAP).
- **Examples**: `DaoAuthenticationProvider`, `JwtAuthenticationProvider`.

### 3. SecurityContext & SecurityContextHolder
- **Responsibility**: Hold security information about the current user.
- **Function**: Stores the `Authentication` object for the current request thread.

### 4. SecurityFilterChain
- **Responsibility**: Defines a sequence of filters that apply security logic to HTTP requests.
- **Function**: Replaces `WebSecurityConfigurerAdapter` in Spring Security 6.

### 5. HttpSecurity
- **Responsibility**: Fluent API for configuring HTTP security.
- **Function**: Used to define security rules like CORS, CSRF, sessions, and request authorization.

### 6. FilterSecurityInterceptor
- **Responsibility**: The last filter in the security filter chain.
- **Function**: Makes authorization decisions based on user roles and permissions.

### 7. AccessDecisionManager
- **Responsibility**: Handles access control decisions.
- **Function**: Delegates to `AccessDecisionVoter`s to evaluate permissions.

### 8. UserDetailsService
- **Responsibility**: Retrieves user-specific data during authentication.
- **Function**: Loads `UserDetails` by username.

### 9. PasswordEncoder
- **Responsibility**: Provides password hashing and verification.
- **Function**: Secures password storage and comparison.
- **Common Implementation**: `BCryptPasswordEncoder`.

### 10. CsrfFilter
- **Responsibility**: Protects against Cross-Site Request Forgery (CSRF) attacks.
- **Function**: Ensures valid CSRF tokens for state-changing requests.

## Authentication request flow (signin or signup)
![Image](https://github.com/user-attachments/assets/8b35f1d3-a36d-4953-9f85-0db1bedbd466)

Request like signin (or) signup are allowed bypass authentication like (Inside security config): 
```declarative
 @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers("/signup","/signin") //bypass the matching request from authentication flow
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                )
```
So request receive to controller and hence to service. Service will verify the user using the userdetails service by communication with authentication manager (code snippet from service):
```declarative
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
```

### Tokenized request flow (signin)
![Image](https://github.com/user-attachments/assets/f84bea2b-8298-4862-a553-7bfc69d6bc95)

Using the JWT Filter(Custom once per request filter for jwt) The security context will hold user details to avoid overhead of to get userdetails from token again. Hence tokenised request mostly refer security context for verification and userdetails. (Code snippet from JWTFilter)
```declarative
@Override
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    //Get the token from Authorization header & username from inside the token
    String authHeader = request.getHeader("Authorization");
    String token = null;
    String username = null;
    if(authHeader!=null && authHeader.startsWith("Bearer ")){
        token = authHeader.substring(7);
        username = jwtUtil.extractUsername(token);
    }

    //If not exist in security context
    if(username != null && SecurityContextHolder.getContext().getAuthentication()==null){
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

        //Validate token and store in security context
        if( jwtUtil.validateToken(username,userDetails,token)){
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
    }
    filterChain.doFilter(request,response);
}
```