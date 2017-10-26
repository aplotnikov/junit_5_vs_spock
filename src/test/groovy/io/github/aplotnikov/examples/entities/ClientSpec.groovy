package io.github.aplotnikov.examples.entities

import spock.lang.Specification

class ClientSpec extends Specification {

    void 'client should have correct first name and second name'() {
        given:
            String firstName = 'Andrii'
            String secondName = 'Plotnikov'
        when:
            Client client = new Client(firstName, secondName)
        then:
            with(client) {
                it.firstName == firstName
                it.secondName == secondName
            }
    }

    void 'client should have correct first name and second name 2'() {
        given:
            String firstName = 'Andrii'
            String secondName = 'Plotnikov'
        when:
            Client client = new Client(firstName, secondName)
        then:
            verifyAll {
                client.firstName == firstName
                client.secondName == secondName
            }
    }

}
