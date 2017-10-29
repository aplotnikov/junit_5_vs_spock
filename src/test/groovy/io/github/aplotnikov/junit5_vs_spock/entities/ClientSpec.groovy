package io.github.aplotnikov.junit5_vs_spock.entities

import static java.math.BigDecimal.ONE
import static java.math.BigDecimal.TEN

import nl.jqno.equalsverifier.EqualsVerifier
import spock.lang.Ignore
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Timeout
import spock.lang.Title

@Title('Unit test for client entity')
class ClientSpec extends Specification {

    @Shared
    String firstName = 'Andrii'

    @Shared
    String secondName = 'Plotnikov'

    @Subject
    Client client = new Client(firstName, secondName, ['test@gmail.com', 'test2@gmail.com'])

    void 'client should have correct first name and second name'() {
        expect:
            with(client) {
                it.firstName == firstName
                it.secondName == secondName
            }
    }

    void 'client should have correct first name and second name 2'() {
        expect:
            verifyAll {
                client.firstName == firstName
                client.secondName == secondName
            }
    }

    void 'client should be not able to take a loan when he has unknown status'() {
        when:
            client.takeLoan(TEN)
        then:
            IllegalStateException exception = thrown(IllegalStateException)
            exception.message == 'In order to take a lona client should have status identified. Current status is UNKNOWN'
    }

    void 'client should be not able to take a loan when he has registered status'() {
        given:
            client.register()
        when:
            client.takeLoan(TEN)
        then:
            IllegalStateException exception = thrown(IllegalStateException)
            exception.message == 'In order to take a lona client should have status identified. Current status is REGISTERED'
    }

    void 'client should not have enough money to take a loan'() {
        given:
            client.identify()
        when:
            client.takeLoan(TEN)
        then:
            IllegalStateException exception = thrown(IllegalStateException)
            exception.message == 'Client does not have enough money'
    }

    void 'client should have enough money to take a loan'() {
        given:
            client.identify()
        when:
            client.takeLoan(ONE)
        then:
            noExceptionThrown()
    }

    @Ignore
    void 'test should be ignored'() {
        expect:
            throw new IllegalStateException('This test should be not launched')
    }

    void 'client should have correct e-mail addresses'() {
        expect:
            client.emails == ['test@gmail.com', 'test2@gmail.com']
    }

    @Timeout(2)
    void 'client should pay in max 2 seconds'() {
        expect:
            client.pay(TEN)
    }

    void 'client should follow equal and hashcode convention'() {
        expect:
            EqualsVerifier.forClass(Client)
                    .withIgnoredFields('status')
                    .verify()
    }
}
