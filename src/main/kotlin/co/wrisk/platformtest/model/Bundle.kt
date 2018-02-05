package co.wrisk.platformtest.model

import java.util.*

enum class Bundle(val type: ItemType, val values: List<MonetaryAmount>) {
    General(ItemType.General, listOf("2500", "5000", "10000", "15000").map { MonetaryAmount(it, Currency.getInstance("GBP")) }),
    Jewelry(ItemType.Jewelry, listOf("1000", "2000", "3000", "4000", "5000").map { MonetaryAmount(it, Currency.getInstance("GBP")) })
}