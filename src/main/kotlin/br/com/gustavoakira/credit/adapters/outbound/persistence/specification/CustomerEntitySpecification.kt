package br.com.gustavoakira.credit.adapters.outbound.persistence.specification

import br.com.gustavoakira.credit.adapters.outbound.persistence.entities.CustomerEntity
import br.com.gustavoakira.credit.application.criteria.CustomerFilter
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Root
import org.springframework.data.jpa.domain.Specification

data class CustomerEntitySpecification(private val filter: CustomerFilter) : Specification<CustomerEntity> {
    override fun toPredicate(
        root: Root<CustomerEntity>,
        query: CriteriaQuery<*>?,
        criteriaBuilder: CriteriaBuilder
    ): Predicate? {
        val predicates = mutableListOf<Predicate>()

        filter.name?.let {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%${it.lowercase()}%"))
        }

        filter.minAnnualIncome?.let {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("annualIncome"), it))
        }

        filter.maxAnnualIncome?.let {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("annualIncome"), it))
        }

        filter.minCreditScore?.let {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("creditScore"), it))
        }

        filter.email?.let {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), "%${it.lowercase()}%"))
        }

        return criteriaBuilder.and(*predicates.toTypedArray())
    }


}