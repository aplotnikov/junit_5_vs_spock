package io.github.aplotnikov.examples.entities;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ClientTest {

    @Test
    void shouldHaveCorrectFirstNameAndSecondName() {
        // given
        String firstName = "Andrii";
        String secondName = "Plotnikov";
        // when
        Client client = new Client(firstName, secondName);
        // then
        assertEquals(firstName, client.getFirstName());
        assertEquals(secondName, client.getSecondName());
    }

    @Test
    void shouldHaveCorrectFirstNameAndSecondName2() {
        // given
        String firstName = "Andrii";
        String secondName = "Plotnikov";
        // when
        Client client = new Client(firstName, secondName);
        // then
        assertThat(client.getFirstName()).isEqualTo(firstName);
        assertThat(client.getSecondName()).isEqualTo(secondName);
    }

    @Test
    void shouldHaveCorrectFirstNameAndSecondName3() {
        // given
        String firstName = "Andrii";
        String secondName = "Plotnikov";
        // when
        Client client = new Client(firstName, secondName);
        // then
        assertAll(
                () -> assertThat(client.getFirstName()).isEqualTo(firstName),
                () -> assertThat(client.getSecondName()).isEqualTo(secondName)
        );
    }
}