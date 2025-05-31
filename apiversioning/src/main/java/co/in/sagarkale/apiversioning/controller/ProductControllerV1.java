package co.in.sagarkale.apiversioning.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
public class ProductControllerV1 {

    @GetMapping
    public String getProducts() {
        return "Products from API v1 (controller-level)";
    }

}
