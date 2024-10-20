package com.jeno.ecommerce_backend_api.service;

import com.jeno.ecommerce_backend_api.entity.Product;
import com.jeno.ecommerce_backend_api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //Get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    //Get a product by its ID
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    //create new product
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    //update a product by its ID
    public Product updateProduct(Long id, Product productDetails) {
        if(productRepository.existsById(id)) {
            productDetails.setId(id);
            return productRepository.save(productDetails);
        }
        return null;
    }

    //delete a product by its ID
    public boolean deleteProduct(Long id) {
        if(productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }


}
