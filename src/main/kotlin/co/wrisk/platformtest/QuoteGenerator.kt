package co.wrisk.platformtest

import co.wrisk.platformtest.model.*
import java.math.BigDecimal

val Wmax = BigDecimal("1000")
val magicNumber = BigDecimal("0.0015")

fun generateQuote(policyHolder: PolicyHolder, bundles: List<Bundle>, namedItems: List<NamedItem>): List<ItemQuote> {

    val bundleQuotes = bundles.flatMap { bundle ->
        bundle.type.excessOptions.flatMap { excess ->
            bundle.values.map { value ->
                val price = calculatePrice(policyHolder.wriskScore, value, excess, bundle.type.multiplier)
                ItemQuote("${bundle.type} bundle", value, excess, price, bundle.type, true)
            }
        }
    }

    val namedQuotes = namedItems.flatMap { item ->
        item.type.excessOptions.map { excess ->
            val price = calculatePrice(policyHolder.wriskScore, item.value, excess, item.type.multiplier)
            ItemQuote(item.name, item.value, excess, price, item.type, false)
        }
    }

    return bundleQuotes + namedQuotes
}

internal fun calculatePrice(wriskScore: Int, value: MonetaryAmount, excess: MonetaryAmount, multiplier: BigDecimal): MonetaryAmount {
    val result = (Wmax / BigDecimal(wriskScore)) * value.value * (BigDecimal.ONE - (excess / value).value) * magicNumber * multiplier
    return MonetaryAmount(result, excess.currencyType)
}
