package io.github.aplotnikov.junit5_vs_spock.entities

import static io.github.aplotnikov.junit5_vs_spock.entities.Status.IDENTIFIED
import static io.github.aplotnikov.junit5_vs_spock.entities.Status.REGISTERED
import static io.github.aplotnikov.junit5_vs_spock.entities.Status.UNKNOWN
import static java.math.BigDecimal.TEN
import static java.util.concurrent.TimeUnit.SECONDS

import groovy.transform.Canonical

@Canonical
class Client {

    String firstName

    String secondName

    List<String> emails

    private Status status = UNKNOWN

    boolean isUnknown() {
        status == UNKNOWN
    }

    boolean isRegistered() {
        status == REGISTERED
    }

    void register() {
        status = REGISTERED
    }

    boolean isIdentified() {
        status == IDENTIFIED
    }

    void identify() {
        status = IDENTIFIED
    }

    void takeLoan(BigDecimal amount) {
        if (!isIdentified()) {
            throw new IllegalStateException(
                    "In order to take a lona client should have status identified. Current status is $status"
            )
        }

        if (amount >= TEN) {
            throw new IllegalStateException('Client does not have enough money')
        }

        println "Client took loan with principal $amount"
    }

    void pay(BigDecimal amount) {
        sleep SECONDS.toMillis(1)
        println "Client paid $amount"
    }
}
