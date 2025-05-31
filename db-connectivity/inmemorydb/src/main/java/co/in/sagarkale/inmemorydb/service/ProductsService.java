package co.in.sagarkale.inmemorydb.service;

import co.in.sagarkale.inmemorydb.dto.ProductDTO;
import co.in.sagarkale.inmemorydb.entity.Product;
import co.in.sagarkale.inmemorydb.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductsService {

    @Autowired
    private ProductsRepository productsRepository;

    public Product getProductDetailsById(Long productId){
        return productsRepository.findById(productId).orElseThrow(()-> new RuntimeException("Product Not Found"));
    }

    public Product addNewProduct(ProductDTO productDTO){

        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());

        return productsRepository.save(product);
    }
}
