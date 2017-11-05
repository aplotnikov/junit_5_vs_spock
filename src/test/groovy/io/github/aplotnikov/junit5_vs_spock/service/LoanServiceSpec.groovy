package io.github.aplotnikov.junit5_vs_spock.service

import static io.github.aplotnikov.junit5_vs_spock.entities.Term.days
import static io.github.aplotnikov.junit5_vs_spock.entities.Term.years

import io.github.aplotnikov.junit5_vs_spock.entities.Application
import io.github.aplotnikov.junit5_vs_spock.entities.Loan
import io.vavr.control.Validation
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

@Unroll
class LoanServiceSpec extends Specification {

    @Subject
    LoanService service = new LoanService()

    void 'application should not pass validation when amount is #amount and term is #term'() {
        given:
            Application application = new Application(amount, term)
        when:
            Validation<String, Loan> result = service.create(application)
        then:
            with(result) {
                invalid
                error == violation
            }
        where:
            amount | term     || violation
            0      | days(30) || 'Application amount is less than zero. Provided amount is 0'
            10     | years(1) || 'Application term is bigger than 3 months. Provided term is 365 days'
    }

    void 'application should pass validation and loan is created'() {
        given:
            Application application = new Application(10, days(30))
        when:
            Validation<String, Loan> result = service.create(application)
        then:
            result.valid
        and:
            with(result.get()) {
                amount == application.amount
                term == application.term
            }
    }
}
