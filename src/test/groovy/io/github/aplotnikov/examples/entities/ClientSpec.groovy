package io.github.aplotnikov.examples.entities

import spock.lang.Ignore
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject
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

    void 'client should not have enough money to take a loan'() {
        when:
            client.takeLoan(10)
        then:
            IllegalStateException exception = thrown(IllegalStateException)
            exception.message == 'Client does not have enough money'
    }

    void 'client should have enough money to take a loan'() {
        when:
            client.takeLoan(1)
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
}
