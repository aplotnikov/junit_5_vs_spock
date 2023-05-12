package io.github.aplotnikov.junit5.vs.spock.entities;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TEN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

/**
 * Warning: Order isn't guarantied. According to github it should be fixed into JUnit 5.1.
 */
@TestInstance(PER_CLASS)
class ClientInstancePerClassTest {

    private static final String FIRST_NAME = "Andrii";

    private static final String SECOND_NAME = "Plotnikov";

    private Client client = new Client(FIRST_NAME, SECOND_NAME, List.of("test@gmail.com", "test2@gmail.com"));

    @Test
    @DisplayName("Client should have status unknown after creation")
    void shouldBeUnknown() {
        assertThat(client.isUnknown()).isTrue();
    }

    @Test
    @DisplayName("Client should have correct first name and second name - assertAll default assertion")
    void shouldHaveCorrectFirstNameAndSecondName3() {
        assertAll(
            () -> assertThat(client.getFirstName()).isEqualTo(FIRST_NAME),
            () -> assertThat(client.getSecondName()).isEqualTo(SECOND_NAME)
        );
    }

    @Test
    @DisplayName("Client should have status registered")
    void shouldBeRegistered() {
        client.register();

        assertThat(client.isRegistered()).isTrue();
    }

    @Test
    @DisplayName("Client should have status identified")
    void shouldBeIdentified() {
        client.identify();

        assertThat(client.isIdentified()).isTrue();
    }

    @Test
    @DisplayName("Client should not have enough money to take a loan")
    void shouldThrowIllegalStateException() {
        assertThatThrownBy(() -> client.takeLoan(TEN))
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("Client does not have enough money");
    }

    @Test
    @DisplayName("Client should have enough money to take a loan")
    void shouldNotThrowIllegalStateException() {
        assertThatCode(() -> client.takeLoan(ONE)).doesNotThrowAnyException();
    }
}
