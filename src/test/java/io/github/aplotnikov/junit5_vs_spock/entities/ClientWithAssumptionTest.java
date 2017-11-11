package io.github.aplotnikov.junit5_vs_spock.entities;

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
    @DisplayName("Test should be skipped when OS is Windows - assumeTrue method")
    void shouldSkipTestWhenOsIsWindows() {
        assumeTrue(
                System.getProperty("os.name").startsWith("Windows"),
                () -> "Aborting test: test was launched on Windows"
        );

        assertThat(client.getFirstName()).isEqualTo(FIRST_NAME);
        assertThat(client.getSecondName()).isEqualTo(SECOND_NAME);
    }

    @Test
    @DisplayName("Test should be skipped when OS is Mac OS - assumeTrue method")
    void shouldSkipTestWhenOsIsMacOs() {
        assumeTrue(
                System.getProperty("os.name").startsWith("Mac OS"),
                () -> "Aborting test: test was launched on Mac OS"
        );

        assertThat(client.getFirstName()).isEqualTo(FIRST_NAME);
        assertThat(client.getSecondName()).isEqualTo(SECOND_NAME);
    }

    @Test
    @DisplayName("Test should be skipped when OS is Linux - assumeTrue method")
    void shouldSkipTestWhenOsIsLinux() {
        assumeTrue(
                System.getProperty("os.name").startsWith("Linux"),
                () -> "Aborting test: test was launched on Linux"
        );

        assertThat(client.getFirstName()).isEqualTo(FIRST_NAME);
        assertThat(client.getSecondName()).isEqualTo(SECOND_NAME);
    }

    @Test
    @DisplayName("Part of test should be skipped when OS is not Windows - assumingThat method")
    void shouldSkipTestWhenOsIsWindows2() {
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
    @DisplayName("Part of test should be skipped when OS is not Mac OS - assumingThat method")
    void shouldSkipTestWhenOsIsMac2() {
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
    @DisplayName("Part of test should be skipped when OS is not Linux - assumingThat method")
    void shouldSkipTestWhenOsIsLinux2() {
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
