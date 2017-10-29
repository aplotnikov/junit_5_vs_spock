package io.github.aplotnikov.junit5_vs_spock.entities;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TEN;
import static java.time.Duration.ofSeconds;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

@DisplayName("Unit tests for Client entity")
class ClientTest {

    private static final String FIRST_NAME = "Andrii";

    private static final String SECOND_NAME = "Plotnikov";

    private final Client client = new Client(FIRST_NAME, SECOND_NAME, asList("test@gmail.com", "test2@gmail.com"));

    @BeforeAll
    static void setUpClass() {
        System.out.println("Main class is prepared");
    }

    @AfterAll
    static void tearDownClass() {
        System.out.println("Main class is cleaned");
    }

    @BeforeEach
    void setUpTest() {
        System.out.println("Test into " + getClass().getSimpleName() + " class is prepared");
    }

    @AfterEach
    void tearDownTest() {
        System.out.println("Test into " + getClass().getSimpleName() + " class is cleaned");
    }

    @Test
    @DisplayName("Client should pay in max 2 seconds")
    void shouldPayDuringTwoSeconds() {
        assertTimeout(ofSeconds(2), () -> client.pay(TEN));
    }

    @Nested
    @DisplayName("When entity is created")
    class DefaultStateTest {

        @BeforeEach
        void setUp() {
            System.out.println("Test into " + getClass().getSimpleName() + " class is prepared");
        }

        @AfterEach
        void tearDown() {
            System.out.println("Test into " + getClass().getSimpleName() + " class is cleaned");
        }

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
        @DisplayName("Client should have status unknown after creation")
        void shouldBeUnknown() {
            assertThat(client.isUnknown()).isTrue();
        }

        @Test
        @Disabled
        @DisplayName("Test should be ignored")
        void shouldIgnoreTest() {
            throw new IllegalStateException("This test should be not launched");
        }

        @Test
        @DisplayName("Client should have correct e-mail addresses")
        void shouldHaveCorrectEmails() {
            assertLinesMatch(asList("test@gmail.com", "test2@gmail.com"), client.getEmails());
        }
    }

    @Nested
    @DisplayName("When registration is completed")
    class RegistrationTest {

        @BeforeEach
        void setUp() {
            System.out.println("Test into " + getClass().getSimpleName() + " class is prepared");
            client.register();
        }

        @AfterEach
        void tearDown() {
            System.out.println("Test into " + getClass().getSimpleName() + " class is cleaned");
        }

        @Test
        @DisplayName("Client should have status registered")
        void shouldBeRegistered() {
            assertThat(client.isRegistered()).isTrue();
        }

        @Test
        @DisplayName("Client should be not able to take a loan - IllegalStateException is thrown")
        void shouldBeNotAbleToTakeALoan() {
            assertThatThrownBy(() -> client.takeLoan(TEN))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage(
                            "In order to take a lona client should have status identified. Current status is REGISTERED"
                    );
        }

        @Nested
        @DisplayName("When identification is completed")
        class IdentificationTest {

            @BeforeEach
            void setUp() {
                System.out.println("Test into " + getClass().getSimpleName() + " class is prepared");
                client.identify();
            }

            @AfterEach
            void tearDown() {
                System.out.println("Test into " + getClass().getSimpleName() + " class is cleaned");
            }

            @Test
            @DisplayName("Client should have status identified")
            void shouldBeIdentified() {
                assertThat(client.isIdentified()).isTrue();
            }

            @Test
            @DisplayName("Client should not have enough money to take a loan")
            void shouldThrowIllegalStateException() {
                IllegalStateException exception = assertThrows(IllegalStateException.class, () -> client.takeLoan(TEN));
                assertThat(exception.getMessage()).isEqualTo("Client does not have enough money");
            }

            @Test
            @DisplayName("Client should not have enough money to take a loan - assertj assertion")
            void shouldThrowIllegalStateException2() {
                Throwable exception = catchThrowable(() -> client.takeLoan(TEN));
                assertThat(exception)
                        .isInstanceOf(IllegalStateException.class)
                        .hasMessage("Client does not have enough money");
            }

            @Test
            @DisplayName("Client should not have enough money to take a loan - assertj assertion")
            void shouldThrowIllegalStateException3() {
                assertThatThrownBy(() -> client.takeLoan(TEN))
                        .isInstanceOf(IllegalStateException.class)
                        .hasMessage("Client does not have enough money");
            }

            @Test
            @DisplayName("Client should not have enough money to take a loan - assertj assertion")
            void shouldThrowIllegalStateException4() {
                assertThatExceptionOfType(IllegalStateException.class)
                        .isThrownBy(() -> client.takeLoan(TEN))
                        .withMessage("Client does not have enough money");
            }

            @Test
            @DisplayName("Client should have enough money to take a loan - assertj assertion")
            void shouldNotThrowIllegalStateException4() {
                assertThatCode(() -> client.takeLoan(ONE)).doesNotThrowAnyException();
            }
        }
    }

    @Nested
    @DisplayName("Entity convention tests")
    class EntityConventionTest {

        @BeforeEach
        void setUp() {
            System.out.println("Test into " + getClass().getSimpleName() + " class is prepared");
        }

        @AfterEach
        void tearDown() {
            System.out.println("Test into " + getClass().getSimpleName() + " class is cleaned");
        }

        @Test
        @DisplayName("Entity should follow equal and hashcode convention")
        void shouldFollowEqualAndHashCodeConvention() {
            EqualsVerifier.forClass(Client.class)
                    .withIgnoredFields("status")
                    .verify();
        }
    }
}
