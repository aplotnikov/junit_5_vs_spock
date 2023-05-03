package io.github.aplotnikov.junit5.vs.spock.repository;

import io.github.aplotnikov.junit5.vs.spock.entities.Loan;

public interface LoanRepository {

    Loan save(Loan loan);
}
