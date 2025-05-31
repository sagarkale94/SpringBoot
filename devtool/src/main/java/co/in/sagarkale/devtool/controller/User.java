package co.in.sagarkale.devtool.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class User {

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return new ResponseEntity<String>("Test URL is working fine",HttpStatus.OK);
    }

}
