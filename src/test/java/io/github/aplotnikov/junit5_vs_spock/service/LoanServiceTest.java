package io.github.aplotnikov.junit5_vs_spock.service;

import static io.github.aplotnikov.junit5_vs_spock.entities.Term.days;
import static io.github.aplotnikov.junit5_vs_spock.entities.Term.term;
import static io.github.aplotnikov.junit5_vs_spock.entities.Term.years;
import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.ZERO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.EnumSource.Mode.EXCLUDE;

import java.math.BigDecimal;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import io.github.aplotnikov.junit5_vs_spock.entities.Application;
import io.github.aplotnikov.junit5_vs_spock.entities.DateUnit;
import io.github.aplotnikov.junit5_vs_spock.entities.Loan;
import io.github.aplotnikov.junit5_vs_spock.entities.Term;
import io.vavr.control.Validation;

class LoanServiceTest {

    private LoanService service = new LoanService();

    private static IntStream amounts() {
        return IntStream.range(-3, 0);
    }

    private static Stream<Arguments> amountAndTerm() {
        return Stream.of(
                Arguments.of(ZERO, days(30), "Application amount is less than zero. Provided amount is 0"),
                Arguments.of(TEN, years(1), "Application term is bigger than 3 months. Provided term is 365 days")
        );
    }

    @DisplayName("Application should not pass validation (string value source) when")
    @ParameterizedTest(name = "{index} ==> amount is {0}")
    @ValueSource(strings = { "-1", "0" })
    void shouldApplicationNotPassValidation(String amount) {
        Application application = new Application(BigDecimal.valueOf(parseInt(amount)), days(30));

        Validation<String, Loan> result = service.create(application);

        assertThat(result.isInvalid()).isTrue();
        assertThat(result.getError())
                .isEqualTo("Application amount is less than zero. Provided amount is " + amount);
    }

    @DisplayName("Application should not pass validation (method int source) when")
    @ParameterizedTest(name = "{index} ==> amount is {0}")
    @MethodSource("amounts")
    void shouldApplicationNotPassValidationWithMethodSource(int amount) {
        Application application = new Application(BigDecimal.valueOf(amount), days(30));

        Validation<String, Loan> result = service.create(application);

        assertThat(result.isInvalid()).isTrue();
        assertThat(result.getError())
                .isEqualTo("Application amount is less than zero. Provided amount is " + amount);
    }

    @DisplayName("Application should not pass validation (int value source) when")
    @ParameterizedTest(name = "{index} ==> amount is {0}")
    @ValueSource(ints = { -1, 0 })
    void shouldApplicationNotPassValidation(int amount) {
        Application application = new Application(BigDecimal.valueOf(amount), days(30));

        Validation<String, Loan> result = service.create(application);

        assertThat(result.isInvalid()).isTrue();
        assertThat(result.getError())
                .isEqualTo("Application amount is less than zero. Provided amount is " + amount);
    }

    @DisplayName("Application should not pass validation (enum source) when")
    @ParameterizedTest(name = "{index} ==> term date unit is {0}")
    @EnumSource(DateUnit.class)
    void shouldApplicationNotPassValidation(DateUnit unit) {
        Application application = new Application(TEN, term(1_000, unit));

        Validation<String, Loan> result = service.create(application);

        assertThat(result.isInvalid()).isTrue();
        assertThat(result.getError())
                .isEqualTo(
                        format("Application term is bigger than 3 months. Provided term is %s days",
                                application.getTerm().getDays())
                );
    }

    @DisplayName("Application should not pass validation (enum source with includes) when")
    @ParameterizedTest(name = "{index} ==> term date unit is {0}")
    @EnumSource(value = DateUnit.class, names = { "DAY", "MONTH" })
    void shouldApplicationNotPassValidationWithProvidedUnits(DateUnit unit) {
        Application application = new Application(TEN, term(1_000, unit));

        Validation<String, Loan> result = service.create(application);

        assertThat(result.isInvalid()).isTrue();
        assertThat(result.getError())
                .isEqualTo(
                        format("Application term is bigger than 3 months. Provided term is %s days",
                                application.getTerm().getDays())
                );
    }

    @DisplayName("Application should not pass validation (enum source with excludes) when")
    @ParameterizedTest(name = "{index} ==> term date unit is {0}")
    @EnumSource(value = DateUnit.class, mode = EXCLUDE, names = { "DAY", "MONTH" })
    void shouldApplicationNotPassValidationWithExcludedUnits(DateUnit unit) {
        Application application = new Application(TEN, term(1_000, unit));

        Validation<String, Loan> result = service.create(application);

        assertThat(result.isInvalid()).isTrue();
        assertThat(result.getError())
                .isEqualTo(
                        format("Application term is bigger than 3 months. Provided term is %s days",
                                application.getTerm().getDays())
                );
    }

    @DisplayName("Application should not pass validation (method source with arguments) when")
    @ParameterizedTest(name = "{index} ==> amount is {0} and term is {1}")
    @MethodSource("amountAndTerm")
    void shouldApplicationNotPassValidationWithExcludedUnits(BigDecimal amount, Term term, String message) {
        Application application = new Application(amount, term);

        Validation<String, Loan> result = service.create(application);

        assertThat(result.isInvalid()).isTrue();
        assertThat(result.getError()).isEqualTo(message);
    }
}