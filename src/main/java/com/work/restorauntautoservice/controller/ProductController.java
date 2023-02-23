package com.work.restorauntautoservice.controller;

import com.work.restorauntautoservice.model.Product;
import com.work.restorauntautoservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/product-find/{id}")
    Optional<Product> findProductById(@PathVariable String id) {
        return productService.findProduct(id);
    }

    @GetMapping("/product-findAll")
    List<Product> findAllProducts() {
        return productService.findAllProducts();
    }

    @PostMapping("/product-create")
    @ResponseStatus(HttpStatus.CREATED)
    Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping("/product-edit/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    Product editProduct(@PathVariable String id, @RequestBody Product product) {
        return productService.editProduct(id, product);
    }

    @DeleteMapping("/product-delete/{id}")
    void deleteProduct(@PathVariable String id) {
       productService.deleteProduct(id);
    }

}
