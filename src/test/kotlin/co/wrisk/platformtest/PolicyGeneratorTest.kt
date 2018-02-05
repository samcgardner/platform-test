package co.wrisk.platformtest

import co.wrisk.platformtest.model.ItemQuote
import co.wrisk.platformtest.model.ItemType
import co.wrisk.platformtest.model.MonetaryAmount
import org.junit.Test
import org.junit.Assert.*
import java.math.BigDecimal
import java.util.*

class PolicyGeneratorTest {

    @Test
    fun generatePolicy() {
        val quotes = listOf(
                    ItemQuote("General Bundle",
                        MonetaryAmount("2500", Currency.getInstance("GBP")),
                        MonetaryAmount("200", Currency.getInstance("GBP")),
                        MonetaryAmount("108", Currency.getInstance("GBP")),
                        ItemType.General,
                true),
                ItemQuote("Wedding Ring",
                        MonetaryAmount("1000", Currency.getInstance("GBP")),
                        MonetaryAmount("100", Currency.getInstance("GBP")),
                        MonetaryAmount("50", Currency.getInstance("GBP")),
                        ItemType.Jewelry,
                        false))
        val policy = generatePolicy(quotes)
        assertEquals(BigDecimal("200.00"), policy.highestExcess)
        assertEquals(BigDecimal("3500.00"), policy.totalSumInsured)
        assertEquals(2, policy.sections.size)
    }
}