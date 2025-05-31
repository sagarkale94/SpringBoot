package co.in.sagarkale.inmemorydb.controller;

import co.in.sagarkale.inmemorydb.dto.ProductDTO;
import co.in.sagarkale.inmemorydb.entity.Product;
import co.in.sagarkale.inmemorydb.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductDetails(@PathVariable Long id){
        return new ResponseEntity<>(
                productsService.getProductDetailsById(id),
                HttpStatus.OK
        );
    }

    @PostMapping ("/products")
    public ResponseEntity<Product> addNewProduct(@RequestBody ProductDTO productDTO){
        return new ResponseEntity<>(
                productsService.addNewProduct(productDTO),
                HttpStatus.OK
        );
    }

}
