package co.wrisk.platformtest.model

import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode
import java.util.*

class MonetaryAmount(value: String, val currencyType: Currency) {

    val value: BigDecimal = BigDecimal(value, MathContext(0, RoundingMode.HALF_EVEN)).setScale(currencyType.defaultFractionDigits)

    constructor(value: BigDecimal, currencyType: Currency) : this(value.toString(), currencyType)

    // You might think you'd get overloaded operators for free by subclassing BigDecimal. You'd be wrong.

    operator fun plus(other: MonetaryAmount): MonetaryAmount {
        require(this.currencyType == other.currencyType)
        return MonetaryAmount(value.plus(other.value), this.currencyType)
    }

    operator fun minus(other: MonetaryAmount): MonetaryAmount {
        require(this.currencyType == other.currencyType)
        return MonetaryAmount(value.minus(other.value), this.currencyType)
    }

    operator fun times(other: MonetaryAmount): MonetaryAmount {
        require(this.currencyType == other.currencyType)
        return MonetaryAmount(value.times(other.value), this.currencyType)
    }

    operator fun div(other: MonetaryAmount): MonetaryAmount {
        require(this.currencyType == other.currencyType)
        return MonetaryAmount(value.div(other.value), this.currencyType)
    }
}
