package co.wrisk.platformtest

import co.wrisk.platformtest.model.*
import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal
import java.util.*

class QuoteGeneratorTest {

    @Test
    fun generateQuote() {
        val holder = PolicyHolder("Jerry Smith", 1)
        val bundles = listOf(Bundle.General)
        val namedItems = listOf(NamedItem("Wedding Ring", ItemType.Jewelry, MonetaryAmount("400", Currency.getInstance("GBP"))))
        val quote = generateQuote(holder, bundles, namedItems)
        assertEquals(15, quote.size)
    }

    @Test
    fun calculatePrice() {
        val result = calculatePrice(1,
                MonetaryAmount("100", Currency.getInstance("GBP")),
                MonetaryAmount("10", Currency.getInstance("GBP")),
                BigDecimal("0.8"))
        assertEquals(BigDecimal("108.00"), result.value)
    }

}