package io.github.aplotnikov.junit5_vs_spock.repository;

import io.github.aplotnikov.junit5_vs_spock.entities.Loan;

public interface LoanRepository {

    Loan save(Loan loan);
}
