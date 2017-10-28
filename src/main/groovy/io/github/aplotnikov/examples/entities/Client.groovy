package io.github.aplotnikov.examples.entities

import static java.math.BigDecimal.TEN
import static java.util.concurrent.TimeUnit.SECONDS

import groovy.transform.Canonical

@Canonical
class Client {

    String firstName

    String secondName

    List<String> emails

    void takeLoan(BigDecimal amount) {
        if (amount >= TEN) {
            throw new IllegalStateException('Client does not have enough money')
        }
    }

    void pay(BigDecimal amount) {
        sleep SECONDS.toMillis(1)
    }
}
