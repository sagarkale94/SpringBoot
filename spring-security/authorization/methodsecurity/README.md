# Method Security

Instead of adding request matcher not authorization done at method level.

Ensure this annotation is added on config class. Code snippet from ```SecurityConfig```

```declarative
@Configuration
@EnableWebSecurity
@EnableMethodSecurity // Without this annotation method security don't work
public class SecurityConfig {
    @Autowired
    private JWTAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
    ...
    ...
```

Now at method level we can define security rules

```declarative
@RestController
public class InvoiceController {

    @GetMapping("/invoice")
    @PreAuthorize("hasAnyRole('ADMIN','USER')") // <==
    public String getInvoiceDetails(){
        return "Invoice : ABC1123 - Sagar Kale - 455 Rs";
    }

    @PostMapping("/invoice")
    @PreAuthorize("hasAuthority('WRITE_PERMISSION')") // <==
    public String addNewInvoice(){
        return "Invoice added successfully";
    }

    @DeleteMapping("/invoice")
    @PreAuthorize("hasAuthority('DELETE_PERMISSION')") // <==
    public String deleteInvoice(){
        return "Invoice deleted successfully";
    }

}
```

This approach is mode scalable and maintainable compared to request matcher.

### Method Security Flow
![Image](https://github.com/user-attachments/assets/4b4038ec-134b-4d29-b897-8318042c57f3)