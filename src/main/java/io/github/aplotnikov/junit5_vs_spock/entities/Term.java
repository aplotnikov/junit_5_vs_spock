package io.github.aplotnikov.junit5_vs_spock.entities;

import static io.github.aplotnikov.junit5_vs_spock.entities.DateUnit.DAY;
import static io.github.aplotnikov.junit5_vs_spock.entities.DateUnit.MONTH;
import static io.github.aplotnikov.junit5_vs_spock.entities.DateUnit.YEAR;

public class Term {

    private final int days;

    private Term(int days) {
        this.days = days;
    }

    public static Term days(int days) {
        return term(days, DAY);
    }

    public static Term months(int months) {
        return term(months, MONTH);
    }

    public static Term years(int years) {
        return term(years, YEAR);
    }

    public static Term term(int number, DateUnit unit) {
        return new Term(unit.toDays(number));
    }

    public int getDays() {
        return days;
    }

    public boolean isGreaterThan(Term anotherTerm) {
        return days > anotherTerm.days;
    }

    @Override
    public String toString() {
        return "Term{days=" + days + '}';
    }
}
