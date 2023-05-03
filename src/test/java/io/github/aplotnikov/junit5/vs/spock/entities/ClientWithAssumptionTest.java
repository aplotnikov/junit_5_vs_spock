package io.github.aplotnikov.junit5.vs.spock.entities;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Unit test with examples of using assumption")
class ClientWithAssumptionTest {

    private static final String FIRST_NAME = "Andrii";

    private static final String SECOND_NAME = "Plotnikov";

    private final Client client = new Client(FIRST_NAME, SECOND_NAME, asList("test@gmail.com", "test2@gmail.com"));

    @Test
    @DisplayName("Test should be skipped when OS isn't Windows - assumeTrue method")
    void shouldSkipTestWhenOsIsNotWindows() {
        assumeTrue(
            System.getProperty("os.name").startsWith("Windows"),
            () -> "Aborting test: test wasn't launched on Windows"
        );

        assertThat(client.getFirstName()).isEqualTo(FIRST_NAME);
        assertThat(client.getSecondName()).isEqualTo(SECOND_NAME);
    }

    @Test
    @DisplayName("Test should be skipped when OS isn't Mac OS - assumeTrue method")
    void shouldSkipTestWhenOsIsNotMacOs() {
        assumeTrue(
            System.getProperty("os.name").startsWith("Mac OS"),
            () -> "Aborting test: test wasn't launched on Mac OS"
        );

        assertThat(client.getFirstName()).isEqualTo(FIRST_NAME);
        assertThat(client.getSecondName()).isEqualTo(SECOND_NAME);
    }

    @Test
    @DisplayName("Test should be skipped when OS isn't Linux - assumeTrue method")
    void shouldSkipTestWhenOsIsNotLinux() {
        assumeTrue(
            System.getProperty("os.name").startsWith("Linux"),
            () -> "Aborting test: test wasn't launched on Linux"
        );

        assertThat(client.getFirstName()).isEqualTo(FIRST_NAME);
        assertThat(client.getSecondName()).isEqualTo(SECOND_NAME);
    }

    @Test
    @DisplayName("Part of test should be skipped when OS isn't Windows - assumingThat method")
    void shouldSkipTestWhenOsIsNotWindows2() {
        assumingThat(
            System.getProperty("os.name").startsWith("Windows"),
            () -> {
                System.out.println("Inner assertion is called");
                assertThat(client.getFirstName()).isEqualTo(FIRST_NAME);
            }
        );

        System.out.println("Main assertion is called");
        assertThat(client.getSecondName()).isEqualTo(SECOND_NAME);
    }

    @Test
    @DisplayName("Part of test should be skipped when OS isn't Mac OS - assumingThat method")
    void shouldSkipTestWhenOsIsNotMac2() {
        assumingThat(
            System.getProperty("os.name").startsWith("Mac OS"),
            () -> {
                System.out.println("Inner assertion is called");
                assertThat(client.getFirstName()).isEqualTo(FIRST_NAME);
            }
        );

        System.out.println("Main assertion is called");
        assertThat(client.getSecondName()).isEqualTo(SECOND_NAME);
    }

    @Test
    @DisplayName("Part of test should be skipped when OS isn't Linux - assumingThat method")
    void shouldSkipTestWhenOsIsNotLinux2() {
        assumingThat(
            System.getProperty("os.name").startsWith("Linux"),
            () -> {
                System.out.println("Inner assertion is called");
                assertThat(client.getFirstName()).isEqualTo(FIRST_NAME);
            }
        );

        System.out.println("Main assertion is called");
        assertThat(client.getSecondName()).isEqualTo(SECOND_NAME);
    }
}
