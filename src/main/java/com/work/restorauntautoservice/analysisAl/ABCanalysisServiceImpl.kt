package com.work.restorauntautoservice.analysisAl

import com.work.restorauntautoservice.enums.ProductCategory
import com.work.restorauntautoservice.model.Product
import com.work.restorauntautoservice.service.implementation.ProductServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ABCanalysisServiceImpl(
    @Autowired
    var productService: ProductServiceImpl
) : ABCanalysisService {

    override fun abcAnalysis(
        percentA: Double,
        percentB: Double
    ): Triple<List<Product>, List<Product>, List<Product>> {
        val items = productService.findAllProducts();
        val sortedItems = items.sortedByDescending { it.actualPrice }
        val totalValue = sortedItems.sumOf { it.actualPrice }
        var cumValue = 0.0
        var indexA = -1
        var indexB = -1
        for (i in sortedItems.indices) {
            cumValue += sortedItems[i].actualPrice
            if (cumValue / totalValue >= percentA && indexA == -1) {
                indexA = i
            }
            if (cumValue / totalValue >= percentB && indexB == -1) {
                indexB = i
            }
            if (indexA != -1 && indexB != -1) {
                break
            }
        }
        val itemsA = sortedItems.take(indexA + 1)
        itemsA.map {
            it.category = ProductCategory.A
            productService.editProduct(it.id, it)
        }
        val itemsB = sortedItems.subList(indexA + 1, indexB + 1)
        itemsB.map {
            it.category = ProductCategory.B
            productService.editProduct(it.id, it)
        }
        val itemsC = sortedItems.subList(indexB + 1, sortedItems.size)
        itemsC.map {
            it.category = ProductCategory.C
            productService.editProduct(it.id, it)
        }
        return Triple(itemsA, itemsB, itemsC)
    }
}