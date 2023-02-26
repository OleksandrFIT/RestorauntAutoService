package com.work.restorauntautoservice.service.implementation;

import com.work.restorauntautoservice.model.Product;
import com.work.restorauntautoservice.repository.ProductRepository;
import com.work.restorauntautoservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product editProduct(String id, Product product) {
        Product productToUpdate = productRepository.getById(id);
        productToUpdate.setName(product.getName());
        productToUpdate.setCode(product.getCode());
        productToUpdate.setCategory(product.getCategory());
        productToUpdate.setProvider(product.getProvider());
        productToUpdate.setOrderPrice(product.getOrderPrice());
        productToUpdate.setActualPrice(product.getActualPrice());
        productToUpdate.setActualPrice(product.getActualPrice());
        productToUpdate.setAvailableQuantity(product.getAvailableQuantity());
        return productRepository.save(productToUpdate);
    }

    @Override
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

    @Override
    public Optional<Product> findProduct(String id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }
}
