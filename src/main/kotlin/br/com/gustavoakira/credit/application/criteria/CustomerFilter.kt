package br.com.gustavoakira.credit.application.criteria

data class CustomerFilter(
    val name: String? = null,
    val minAnnualIncome: Double? = null,
    val maxAnnualIncome: Double? = null,
    val minCreditScore: Int? = null,
    val email: String? = null
)