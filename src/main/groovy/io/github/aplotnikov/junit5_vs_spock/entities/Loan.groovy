package io.github.aplotnikov.junit5_vs_spock.entities

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@ToString
@EqualsAndHashCode
class Loan {
    final BigDecimal amount

    final Term term

    Loan(BigDecimal amount, Term term) {
        this.amount = amount
        this.term = term
    }
}
