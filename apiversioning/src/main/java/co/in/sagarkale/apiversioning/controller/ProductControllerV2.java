package co.in.sagarkale.apiversioning.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/products")
public class ProductControllerV2 {

    @GetMapping
    public String getProducts() {
        return "Products from API v2 (controller-level)";
    }

}
