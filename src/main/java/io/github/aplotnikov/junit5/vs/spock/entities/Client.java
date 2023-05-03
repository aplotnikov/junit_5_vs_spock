package io.github.aplotnikov.junit5.vs.spock.entities;

import static java.math.BigDecimal.TEN;
import static java.util.concurrent.TimeUnit.SECONDS;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import io.vavr.control.Try;

public class Client {

    private final String firstName;

    private final String secondName;

    private final List<String> emails;

    private Status status = Status.UNKNOWN;

    public Client(String firstName, String secondName, List<String> emails) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.emails = emails;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public List<String> getEmails() {
        return emails;
    }

    public boolean isUnknown() {
        return status == Status.UNKNOWN;
    }

    public boolean isRegistered() {
        return status == Status.REGISTERED;
    }

    public void register() {
        status = Status.REGISTERED;
    }

    public boolean isIdentified() {
        return status == Status.IDENTIFIED;
    }

    public void identify() {
        status = Status.IDENTIFIED;
    }

    public void takeLoan(BigDecimal amount) {
        if (!isIdentified()) {
            throw new IllegalStateException(
                "In order to take a lona client should have status identified. Current status is %s".formatted(status)
            );
        }

        if (amount.compareTo(TEN) >= 0) {
            throw new IllegalStateException("Client does not have enough money");
        }

        System.out.println("Client took loan with principal " + amount);
    }

    public void pay(BigDecimal amount) {
        Try.run(() -> SECONDS.sleep(1)).onFailure(Throwable::printStackTrace);
        System.out.println("Client paid  " + amount);
    }

    public void payIdentificationFee() {
        pay(BigDecimal.valueOf(0.1));
        identify();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Client client = (Client) o;
        return Objects.equals(firstName, client.firstName)
            && Objects.equals(secondName, client.secondName)
            && Objects.equals(emails, client.emails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, secondName, emails);
    }
}
