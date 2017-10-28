package io.github.aplotnikov.examples.entities;

import static java.math.BigDecimal.TEN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ClientTest {

    private static final String FIRST_NAME = "Andrii";

    private static final String SECOND_NAME = "Plotnikov";

    private Client client = new Client(FIRST_NAME, SECOND_NAME);

    @Test
    @DisplayName("Client should have correct first name and second name - default assertion")
    void shouldHaveCorrectFirstNameAndSecondName() {
        assertEquals(FIRST_NAME, client.getFirstName());
        assertEquals(SECOND_NAME, client.getSecondName());
    }

    @Test
    @DisplayName("Client should have correct first name and second name - assertj assertion")
    void shouldHaveCorrectFirstNameAndSecondName2() {
        assertThat(client.getFirstName()).isEqualTo(FIRST_NAME);
        assertThat(client.getSecondName()).isEqualTo(SECOND_NAME);
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
    @DisplayName("Client should not have enough money to take loan")
    void shouldThrowIllegalStateException() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> client.takeLoan(TEN));
        assertThat(exception.getMessage()).isEqualTo("Client does not have enough money");
    }

    @Test
    @DisplayName("Client should not have enough money to take loan - assertj assertion")
    void shouldThrowIllegalStateException2() {
        Throwable exception = catchThrowable(() -> client.takeLoan(TEN));
        assertThat(exception)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Client does not have enough money");
    }

    @Test
    @DisplayName("Client should not have enough money to take loan - assertj assertion")
    void shouldThrowIllegalStateException3() {
        assertThatThrownBy(() -> client.takeLoan(TEN))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Client does not have enough money");
    }

    @Test
    @DisplayName("Client should not have enough money to take loan - assertj assertion")
    void shouldThrowIllegalStateException4() {
        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(() -> client.takeLoan(TEN))
                .withMessage("Client does not have enough money");
    }
}