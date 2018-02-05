package co.wrisk.platformtest.model

class PolicyHolder(val name: String, val wriskScore: Int) {
    init {
        require(wriskScore in 1..1000)
    }
}