package co.in.sagarkale.globalexceptionhandling.controller;

import co.in.sagarkale.globalexceptionhandling.dto.APIResponseDTO;
import co.in.sagarkale.globalexceptionhandling.entity.User;
import co.in.sagarkale.globalexceptionhandling.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/api/v1/user/{userId}")
    public ResponseEntity<APIResponseDTO<User>> getUserDetails(@PathVariable int userId){
        User user = userService.getUserDetails(userId);

        return new ResponseEntity<>(
                new APIResponseDTO(
                        true,
                        "",
                        user
                ),
                HttpStatus.OK
        );
    }
}
