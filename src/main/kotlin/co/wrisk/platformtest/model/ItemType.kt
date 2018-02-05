package co.wrisk.platformtest.model

import java.math.BigDecimal
import java.util.*

enum class ItemType(val multiplier: BigDecimal, val excessOptions: List<MonetaryAmount>) {
    General(BigDecimal("0.1"), listOf("200", "300", "400").map { MonetaryAmount(it, Currency.getInstance("GBP")) }),
    Jewelry(BigDecimal("2"), listOf("100", "200", "300").map { MonetaryAmount(it, Currency.getInstance("GBP")) }),
    Electronics(BigDecimal("1"), listOf("100", "200", "300", "400", "500").map { MonetaryAmount(it, Currency.getInstance("GBP")) }),
    Bicycles(BigDecimal("0.8"), listOf("300", "400", "500").map { MonetaryAmount(it, Currency.getInstance("GBP")) })
}