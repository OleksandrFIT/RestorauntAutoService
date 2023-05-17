package com.work.restorauntautoservice.service.implementation;

import com.work.restorauntautoservice.analysisAl.CountRestAlgorithmServiceImpl;
import com.work.restorauntautoservice.exception.NotEnoughProductException;
import com.work.restorauntautoservice.model.*;
import com.work.restorauntautoservice.repository.ProductRepository;
import com.work.restorauntautoservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CountRestAlgorithmServiceImpl countRestAlgorithmService;

    @Override
    public Product createProduct(Product product) {
        if (findProductByName(product.getName()).isPresent()) {
            throw new RuntimeException("Product already exists");
        } else {
            return productRepository.save(product);
        }
    }


    /**
        Оновлює всі всі отримані з замовлення продукти (к-ть на складі після створення замовлення)
     */
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
    public Optional<Product> findProductByName(String name) {
        return  productRepository.getProductByName(name);
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> countRestProductsResult() {
        return countRestAlgorithmService.countRestProducts();
    }


    /**
     * @param purchase
     * @return List<Product>
     *
     *    Віднімає всі продукти з замовлення.
     *    проходиться по всьому списку продуктів і перевіряє чи є необіхдна к-ть продуктів на скалі
     *    Якщо ні, то отримуємо помилку.
     *    Якщо так, то віднімаємо к-ть даного продукту необхідну для страви від кількості даного продукті на складі.
     *    Після виконання даної умови, додаємо оновлений продукт в список.
     *    Отриманий список є списком з оновленою наявною к-тю кожного наявного в замовленні продукту на складі.
     *    Цей список з оновленими даними новою наявною к-тю продктів на складі повинен бути збережений до бд
     */
    private List<Product> minusOrderProducts(Purchase purchase) {
        List<Product> allStorageProducts = findAllProducts();

        // Extracts the list of products and their weights from the purchase. This is required for the order.
        List<ProductPair> purchaseProducts = getProductsFromPurchase(purchase);

        List<Product> updatedProductsList = new ArrayList<>();
        for (Product product : allStorageProducts) {
            double availableWeight = product.getAvailableQuantity() * product.getWeight();
            double weightToSubtract = 0;

            for (ProductPair productPair : purchaseProducts) {
                if (productPair.getProduct().equals(product)) {
                    weightToSubtract += productPair.getWeight();
                }
            }

            // Checks if the required product weight is greater than the available weight at the storage
            if (weightToSubtract > availableWeight) {
                double lessAvailableWeight = weightToSubtract - availableWeight;
                throw new NotEnoughProductException("Insufficient quantity for product: " + product.getName()
                        + "\nYou need " + weightToSubtract + "g but only have " + availableWeight + "g available.");
            } else if (weightToSubtract > 0) {
                double updatedQuantity = (availableWeight - weightToSubtract) / product.getWeight();
//                double updatedQuantity = (double) Math.round(updatedWeight / product.getWeight());
                Product updatedProduct = new Product(
                        product.getId(),
                        product.getName(),
                        product.getCode(),
                        product.getCategory(),
                        product.getProvider(),
                        product.getOrderPrice(),
                        product.getActualPrice(),
                        product.getWeight(),
                        updatedQuantity,
                        product.getMaxQuantity(),
                        null);
                updatedProductsList.add(updatedProduct);
            } else {
                updatedProductsList.add(product);
            }
        }
        return updatedProductsList;
    }



    /**
     * @param purchase
     * @return
     *
     * Витягує список продуктів з покупки і їх кількість.
     * Продукти та кількість необхідна для замовлення
     */
    private List<ProductPair> getProductsFromPurchase(Purchase purchase) {
        List<DishPair> dishPairList = purchase.getDishList();
        List<ProductPair> outputProductList = new ArrayList<>();
        for (DishPair dishPair : dishPairList) {
            List<ProductPair> productList = dishPair.getDish().getProductList();
            int quantity = dishPair.getQuantity();
            for (ProductPair productPair : productList) {
                int weight = productPair.getWeight() * quantity;
                Product product = productPair.getProduct();
                // If product already exists in outputProductList, add weight to existing weight
                boolean found = false;
                for (ProductPair outputProductPair : outputProductList) {
                    if (outputProductPair.getProduct().equals(product)) {
                        outputProductPair.setWeight(outputProductPair.getWeight() + weight);
                        found = true;
                        break;
                    }
                }
                // If product doesn't exist in outputProductList, add new product to list
                if (!found) {
                    outputProductList.add(new ProductPair(product, weight));
                }
            }
        }
        return outputProductList;
    }



}
