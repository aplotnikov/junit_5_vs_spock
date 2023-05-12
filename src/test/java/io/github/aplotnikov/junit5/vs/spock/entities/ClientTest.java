package io.github.aplotnikov.junit5.vs.spock.entities;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TEN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

@DisplayName("Unit tests for Client entity")
class ClientTest {

    private static final String FIRST_NAME = "Andrii";

    private static final String SECOND_NAME = "Plotnikov";

    private final Client client = new Client(FIRST_NAME, SECOND_NAME, List.of("test@gmail.com", "test2@gmail.com"));

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
    @DisplayName("Client should have correct first name and second name - default assertion")
    void shouldHaveCorrectFirstNameAndSecondName() {
        assertEquals(FIRST_NAME, client.getFirstName());
        assertEquals(SECOND_NAME, client.getSecondName());
    }

    @Tag("failed")
    @Test
    @DisplayName("Test should not fail when tag is marked as excluded")
    void shouldNotFailWhenTagIsExcluded() {
        throw new IllegalStateException("This exception should be not thrown");
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
            assertLinesMatch(List.of("test@gmail.com", "test2@gmail.com"), client.getEmails());
        }

        @Test
        @DisplayName("Client should have correct e-mail addresses - assertj")
        void shouldHaveCorrectEmails2() {
            assertThat(client.getEmails()).isEqualTo(List.of("test@gmail.com", "test2@gmail.com"));
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

            @RepeatedTest(10)
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
            void shouldNotThrowIllegalStateException() {
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
                .usingGetClass()
                .withIgnoredFields("status")
                .verify();
        }
    }
}
