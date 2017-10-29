package io.github.aplotnikov.junit5_vs_spock.entities

import static java.math.BigDecimal.TEN

import spock.lang.Subject
import spock.lang.Timeout

class SlowClientSpec {

    @Subject
    Client client = new Client('Andrii', 'Plotnikov', ['test@gmail.com', 'test2@gmail.com'])

    @Timeout(2)
    void 'client should pay in max 2 seconds'() {
        expect:
            client.pay(TEN)
    }

}
