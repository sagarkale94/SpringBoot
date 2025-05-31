package co.in.sagarkale.apiversioning.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @GetMapping("/v1")
    public String getProductsV1() {
        return "Products from API v1 (method-level)";
    }

    @GetMapping("/v2")
    public String getProductsV2() {
        return "Products from API v2 (method-level)";
    }

}
