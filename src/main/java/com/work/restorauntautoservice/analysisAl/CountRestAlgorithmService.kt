package com.work.restorauntautoservice.analysisAl

import com.work.restorauntautoservice.model.Product

interface CountRestAlgorithmService {
    fun countRestProducts(): List<Product>
    fun countSumOfRestProducts(products: List<Product>): String
}