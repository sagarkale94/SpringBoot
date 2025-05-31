package co.in.sagarkale.mysqldb.service;

import co.in.sagarkale.mysqldb.dto.ProductDTO;
import co.in.sagarkale.mysqldb.entity.Product;
import co.in.sagarkale.mysqldb.repository.ProductsRepository;
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
