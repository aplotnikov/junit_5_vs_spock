package io.github.aplotnikov.junit5.vs.spock.entities;

import static java.math.BigDecimal.TEN;
import static java.time.Duration.ofSeconds;
import static java.util.Arrays.asList;
import static java.util.concurrent.Executors.newSingleThreadScheduledExecutor;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Durations.TWO_SECONDS;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("slow")
class SlowClientTest {

    private static final String FIRST_NAME = "Andrii";

    private static final String SECOND_NAME = "Plotnikov";

    private Client client = new Client(FIRST_NAME, SECOND_NAME, asList("test@gmail.com", "test2@gmail.com"));

    @Test
    @DisplayName("Client should pay in max 2 seconds")
    void shouldPayDuringTwoSeconds() {
        assertTimeout(ofSeconds(2), () -> client.pay(TEN));
    }

    @Test
    @DisplayName("Client should pay in max 2 seconds")
    void shouldPayDuringTwoSeconds2() {
        assertTimeoutPreemptively(ofSeconds(2), () -> client.pay(TEN));
    }

    @Test
    @DisplayName("Client should pay identification fee in max 2 seconds - assertTimeout")
    void shouldPayIdentificationFeeDuringTwoSecondsWithAssertTimeout() {
        assertTimeout(ofSeconds(2), client::payIdentificationFee);
        assertThat(client.isIdentified()).isTrue();
    }

    @Test
    @DisplayName("Client should pay identification fee in max 2 seconds - assertTimeoutPreemptively")
    void shouldPayIdentificationFeeDuringTwoSecondsWithAssertTimeoutPreemptively() {
        assertTimeoutPreemptively(ofSeconds(2), client::payIdentificationFee);
        assertThat(client.isIdentified()).isTrue();
    }

    @Test
    @DisplayName("Client should pay identification fee in max 2 seconds - executor service")
    void shouldPayIdentificationFeeDuringTwoSecondsWithOwnCode() throws Exception {
        ExecutorService executorService = newSingleThreadScheduledExecutor();
        try {
            Future<?> result = executorService.submit(client::payIdentificationFee);
            result.get(2, SECONDS);

            assertThat(client.isIdentified()).isTrue();
        } finally {
            executorService.shutdown();
        }
    }

    @Test
    @DisplayName("Client should pay identification fee in max 2 seconds - awaitility")
    void shouldPayIdentificationFeeDuringTwoSecondsWithAwaitility() {
        startPaymentOfIdentificationFee(() ->
            await().atMost(TWO_SECONDS).untilAsserted(() -> assertThat(client.isIdentified()).isTrue())
        );
    }

    @Test
    @DisplayName("Client should pay identification fee in max 2 seconds - awaitility 2")
    void shouldPayIdentificationFeeDuringTwoSecondsWithAwaitility2() {
        startPaymentOfIdentificationFee(
            () -> await().atMost(TWO_SECONDS).until(client::isIdentified)
        );
    }

    private void startPaymentOfIdentificationFee(Runnable operation) {
        ExecutorService executorService = newSingleThreadScheduledExecutor();
        try {
            executorService.submit(client::payIdentificationFee);
            operation.run();
        } finally {
            executorService.shutdown();
        }
    }
}
