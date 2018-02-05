package co.wrisk.platformtest

import co.wrisk.platformservice.contracts.BundleContract
import co.wrisk.platformservice.contracts.NamedItemContract
import co.wrisk.platformservice.contracts.PolicyContract
import co.wrisk.platformservice.contracts.SectionContract
import co.wrisk.platformtest.model.ItemQuote
import co.wrisk.platformtest.model.ItemType

fun generatePolicy(quotes: List<ItemQuote>): PolicyContract {
    val highestExcess = quotes.maxBy { it.excess.value }!!.excess
    val totalSumInsured = quotes.map { it.value }.reduce { x, y -> x + y }
    val sectionContracts = quotes.groupBy { it.type }
            .map { buildSectionContract(it) }
    return PolicyContract(highestExcess.value, totalSumInsured.value, sectionContracts)
}

private fun buildSectionContract(sectionEntry: Map.Entry<ItemType, List<ItemQuote>>): SectionContract {
    val sectionName = sectionEntry.key.name

    val sumInsured = sectionEntry.value.map { it.value }.reduce { x, y -> x + y }

    val bundleQuote = sectionEntry.value.firstOrNull { it.isBundle }
    var bundleContract: BundleContract? = null
    if (bundleQuote != null) {
        bundleContract = BundleContract(bundleQuote.value.value, bundleQuote.excess.value)
    }

    val namedQuotes = sectionEntry.value.filter { it.isBundle.not() }
    val namedItemContracts = namedQuotes.map { NamedItemContract(it.name, it.value.value, it.excess.value) }

    return SectionContract(sectionName, sumInsured.value, bundleContract, namedItemContracts)
}
