package co.in.sagarkale.interceptor.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("api/test")
    public ResponseEntity<Object> getTestResponse(){
        System.out.println("Inside controller : ");
        return new ResponseEntity<>("Test Response",HttpStatus.OK);
    }

}
