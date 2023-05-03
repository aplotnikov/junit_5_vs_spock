package io.github.aplotnikov.junit5_vs_spock.entities

import static java.math.BigDecimal.TEN
import static java.util.concurrent.Executors.newSingleThreadScheduledExecutor

import java.util.concurrent.ExecutorService

import io.github.aplotnikov.junit5_vs_spock.annotations.Slow
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Timeout
import spock.util.concurrent.AsyncConditions
import spock.util.concurrent.PollingConditions

class SlowClientSpec extends Specification {

    @Shared
    int acceptableTimeout = 2

    @Subject
    Client client = new Client('Andrii', 'Plotnikov', ['test@gmail.com', 'test2@gmail.com'])

    @Slow
    @Timeout(2)
    void 'client should pay in max 2 seconds'() {
        expect:
            client.pay(TEN)
    }

    @Slow
    void 'client should pay identification fee in max 2 second with async conditions'() {
        given:
            AsyncConditions conditions = new AsyncConditions()
        when:
            startPaymentOfIdentificationFee {
                conditions.evaluate {
                    assert client.identified
                }
            }
        then:
            conditions.await acceptableTimeout
    }

    @Slow
    void 'client should pay identification fee in max 2 second with polling conditions'() {
        given:
            PollingConditions conditions = new PollingConditions(timeout: acceptableTimeout)
        when:
            startPaymentOfIdentificationFee()
        then:
            conditions.eventually {
                client.identified
            }
    }

    private void startPaymentOfIdentificationFee() {
        startPaymentOfIdentificationFee { }
    }

    private void startPaymentOfIdentificationFee(Closure<?> callback) {
        ExecutorService executorService = newSingleThreadScheduledExecutor()
        try {
            executorService.submit { client.payIdentificationFee() }
            executorService.submit { callback.run() }
        } finally {
            executorService.shutdown()
        }
    }

}
