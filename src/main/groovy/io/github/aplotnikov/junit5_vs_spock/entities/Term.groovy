package io.github.aplotnikov.junit5_vs_spock.entities

import static io.github.aplotnikov.junit5_vs_spock.entities.DateUnit.DAY
import static io.github.aplotnikov.junit5_vs_spock.entities.DateUnit.MONTH
import static io.github.aplotnikov.junit5_vs_spock.entities.DateUnit.YEAR

import groovy.transform.Immutable
import groovy.transform.ToString

@Immutable
@ToString(includeNames = true)
class Term {

    final int days

    boolean isGreaterThan(Term anotherTerm) {
        days > anotherTerm.days
    }

    static Term days(int days) {
        term(days, DAY)
    }

    static Term months(int months) {
        term(months, MONTH)
    }

    static Term years(int years) {
        term(years, YEAR)
    }

    static Term term(int number, DateUnit unit) {
        new Term(unit.toDays(number))
    }
}
