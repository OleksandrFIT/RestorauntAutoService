package com.work.restorauntautoservice.service.implementation;

import com.work.restorauntautoservice.analysisAl.ABCanalysisServiceImpl;
import com.work.restorauntautoservice.analysisAl.CountRestAlgorithmServiceImpl;
import com.work.restorauntautoservice.exception.NotEnoughProductException;
import com.work.restorauntautoservice.model.Dish;
import com.work.restorauntautoservice.model.Product;
import com.work.restorauntautoservice.model.Purchase;
import com.work.restorauntautoservice.repository.ProductRepository;
import com.work.restorauntautoservice.service.ProductService;
import kotlin.Triple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ABCanalysisServiceImpl abCanalysisService;
    @Autowired
    private CountRestAlgorithmServiceImpl countRestAlgorithmService;

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void saveProductAfterPurchase(Purchase purchase) {
        List<Product> updatedProductsList = minusOrderProducts(purchase);
        for (Product product :
                updatedProductsList) {
            editProduct(product.getId(), product);
        }
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
        productToUpdate.setAvailableQuantity(product.getAvailableQuantity());
        productToUpdate.setMaxQuantity(product.getMaxQuantity());
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

    @Override
    public Triple<List<Product>, List<Product>, List<Product>> abcAnalisysResult() {
        return abCanalysisService.abcAnalysis(findAllProducts(), 0.8, 0.95);
    }

    @Override
    public List<Product> countRestProductsResult() {
        return countRestAlgorithmService.countRestProducts();
    }

    public List<Product> minusOrderProducts(Purchase purchase) {
        List<Product> allStorageProducts = findAllProducts();
        HashMap<Product, Integer> purchaseProducts = getProductsFromPurchase(purchase);
        int lessAvaliableQuantity;
        List<Product> updatedProductsList = new ArrayList<>();
        for (Product product : allStorageProducts) {
            int quantityToSubtract = purchaseProducts.getOrDefault(product, 0);
            if (quantityToSubtract > product.getAvailableQuantity()) {
                lessAvaliableQuantity = quantityToSubtract - product.getAvailableQuantity();
                throw new NotEnoughProductException("Insufficient quantity for product: " + product.getName()
                        + "\nYou have " + lessAvaliableQuantity + "less than you need to make this purchase");
            } else if (quantityToSubtract > 0) {
                int updatedQuantity = product.getAvailableQuantity() - quantityToSubtract;
                short updatedQuantityShortValue = Short.parseShort(Integer.toString(updatedQuantity));
                Product updatedProduct = new Product(product.getId(), product.getName(), product.getCode(),
                        product.getCategory(), product.getProvider(), product.getOrderPrice(), product.getActualPrice(),
                        updatedQuantityShortValue,
                        product.getMaxQuantity());
                updatedProductsList.add(updatedProduct);
            } else {
                updatedProductsList.add(product);
            }
        }
        return updatedProductsList;
    }

    private HashMap<Product, Integer> getProductsFromPurchase(Purchase purchase) {
        HashMap<Dish, Integer> dishIntegerHashMap = purchase.getDishList();
        HashMap<Product, Integer> productList;
        HashMap<Product, Integer> outputProductSet = null;
        for (Dish dish :
                dishIntegerHashMap.keySet()) {
            productList = dish.getProductList();
            for (Product product :
                    productList.keySet()) {
                int value = productList.get(product);
                // Якщо продукт вже є в новій HashMap, додаємо значення до наявного значення
                if (outputProductSet.containsKey(product)) {
                    int sum = outputProductSet.get(product) + value;
                    outputProductSet.put(product, sum);
                    // Якщо продукту немає в новій HashMap, записуємо його та його значення
                } else {
                    outputProductSet.put(product, value);
                }
            }
        }
        return outputProductSet;
    }

}
