package co.wrisk.platformtest.model

data class ItemQuote(val name: String,
                     val value: MonetaryAmount,
                     val excess: MonetaryAmount,
                     val price: MonetaryAmount,
                     val type: ItemType,
                     val isBundle: Boolean
)