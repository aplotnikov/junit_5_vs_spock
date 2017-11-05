package io.github.aplotnikov.junit5_vs_spock.entities;

import java.math.BigDecimal;

public class Application {

    private final BigDecimal amount;

    private final Term term;

    public Application(BigDecimal amount, Term term) {
        this.amount = amount;
        this.term = term;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Term getTerm() {
        return term;
    }
}
