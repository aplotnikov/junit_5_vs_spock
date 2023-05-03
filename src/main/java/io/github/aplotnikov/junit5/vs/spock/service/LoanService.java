package io.github.aplotnikov.junit5.vs.spock.service;

import static io.github.aplotnikov.junit5.vs.spock.entities.Term.months;
import static io.vavr.control.Validation.combine;
import static java.math.BigDecimal.ZERO;
import static java.util.stream.Collectors.joining;

import java.math.BigDecimal;
import java.util.function.Function;
import java.util.function.Predicate;

import io.github.aplotnikov.junit5.vs.spock.entities.Application;
import io.github.aplotnikov.junit5.vs.spock.entities.Loan;
import io.github.aplotnikov.junit5.vs.spock.entities.Term;
import io.github.aplotnikov.junit5.vs.spock.repository.LoanRepository;
import io.vavr.collection.Seq;
import io.vavr.control.Option;
import io.vavr.control.Validation;

public class LoanService {

    private static final Term MAX_TERM = months(3);

    private final LoanRepository repository;

    public LoanService(LoanRepository repository) {
        this.repository = repository;
    }

    public Validation<String, Loan> create(Application application) {
        return combine(
            validateAmount(application.getAmount()),
            validateTerm(application.getTerm())
        ).ap(Loan::new)
            .map(repository::save)
            .mapError(collectErrors());
    }

    private Validation<String, BigDecimal> validateAmount(BigDecimal amount) {
        return Option.of(amount)
            .filter(isGreaterThanZero())
            .toValidation("Application amount is less than zero. Provided amount is " + amount);
    }

    private Predicate<BigDecimal> isGreaterThanZero() {
        return amount -> amount.compareTo(ZERO) > 0;
    }

    private Validation<String, Term> validateTerm(Term term) {
        return Option.of(term)
            .filter(MAX_TERM::isGreaterThan)
            .toValidation("Application term is bigger than 3 months. Provided term is " + term.getDays() + " days");
    }

    private Function<Seq<String>, String> collectErrors() {
        return errors -> errors.collect(joining(","));
    }
}
