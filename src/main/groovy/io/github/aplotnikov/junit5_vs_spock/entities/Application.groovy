package io.github.aplotnikov.junit5_vs_spock.entities

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@ToString
@EqualsAndHashCode
class Application {
    final BigDecimal amount

    final Term term

    Application(BigDecimal amount, Term term) {
        this.amount = amount
        this.term = term
    }
}
