package com.work.restorauntautoservice.analysisAl

import com.work.restorauntautoservice.enums.DayOfWeek
import com.work.restorauntautoservice.model.Product
import com.work.restorauntautoservice.repository.ProductRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CountRestAlgorithmServiceImpl(
    private var productRepository: ProductRepository,
): CountRestAlgorithmService {

    override fun countRestProducts(): List<Product> {
        val productList = productRepository.findAll()
        val orderList = mutableListOf<Product>()

        val percentage = getDayOfTomorrowsDayOfWeek().percentage



        productList.forEach {
            if (it.availableQuantity < it.maxQuantity * percentage / 100) {
                it.amountToBuy = (it.maxQuantity * (percentage + 10) / 100).toShort()
                orderList.add(it)
            } else {
                println("The ${it.name} product is enough in storage for tomorrow's day")
            }
        }

        return orderList
    }

    override fun countSumOfRestProducts(products: List<Product>): String {
        val percentage = getDayOfTomorrowsDayOfWeek().percentage
        var sum = 0.0
        products.forEach {
            sum += it.actualPrice * (it.maxQuantity - it.availableQuantity) * percentage / 100
        }
        return sum.toString()
    }

    fun generateUniqueCode(): String {
        val uuid = UUID.randomUUID().toString()
        return uuid.substring(0, 8)
    }

    /**
     * повертає наступний день тижня з відповідним відсотком
     */
    private fun getDayOfTomorrowsDayOfWeek(): DayOfWeek {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_WEEK, 1)
        val tomorrow = calendar.get(Calendar.DAY_OF_WEEK)
        return getDayOfWeek(tomorrow)
    }

    private fun getDayOfWeek(day: Int): DayOfWeek {
        return when (day) {
            Calendar.SUNDAY -> DayOfWeek.SUNDAY
            Calendar.MONDAY -> DayOfWeek.MONDAY
            Calendar.TUESDAY -> DayOfWeek.TUESDAY
            Calendar.WEDNESDAY -> DayOfWeek.WEDNESDAY
            Calendar.THURSDAY -> DayOfWeek.THURSDAY
            Calendar.FRIDAY -> DayOfWeek.FRIDAY
            Calendar.SATURDAY -> DayOfWeek.SATURDAY
            else -> throw IllegalArgumentException("Invalid day of week")
        }
    }
}