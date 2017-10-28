package io.github.aplotnikov.examples.entities;

import static java.math.BigDecimal.TEN;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Client {

    private final String firstName;

    private final String secondName;

    private final List<String> emails;

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

    public void takeLoan(BigDecimal amount) {
        if (amount.compareTo(TEN) > -1) {
            throw new IllegalStateException("Client does not have enough money");
        }
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
                && Objects.equals(secondName, client.secondName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, secondName);
    }
}
