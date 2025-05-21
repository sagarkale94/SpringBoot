package co.in.sagarkale.filter.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/api/test")
    public ResponseEntity<Object> getTestResponse(){
        System.out.println("Inside the controller");

        return new ResponseEntity<>("This is test response",HttpStatus.OK);
    }

}
