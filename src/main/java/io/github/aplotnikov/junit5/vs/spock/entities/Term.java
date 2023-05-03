package io.github.aplotnikov.junit5.vs.spock.entities;

public class Term {

    private final int days;

    private Term(int days) {
        this.days = days;
    }

    public static Term days(int days) {
        return term(days, DateUnit.DAY);
    }

    public static Term months(int months) {
        return term(months, DateUnit.MONTH);
    }

    public static Term years(int years) {
        return term(years, DateUnit.YEAR);
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
