package org.lotto.http.numbergenerator;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.http.Fault;
import org.junit.jupiter.api.Test;
import org.lotto.domain.numbergenerator.WinningNumbersGenerable;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertAll;

class RandomNumberGeneratorRestTemplateIntegrationTest extends RandomNumberGeneratorRestTemplateIntegrationTestConfig {
    WinningNumbersGenerable winningNumbersGenerable = remoteNumberGeneratorClient(restTemplateResponseErrorHandler());
    private final String CONTENT_TYPE = "Content-Type";
    private final String APPLICATION_JSON_CONTENT_TYPE = "application/json";
    @Test
    void should_throw_exception_when_fault_CONNECTION_RESET_BY_PEER() {

        wireMockServer.stubFor(WireMock.get("/api/v1.0/random?min=1&max=99&count=25")
                        .willReturn(WireMock.aResponse()
                                .withStatus(HttpStatus.OK.value())
                                .withHeader(CONTENT_TYPE, APPLICATION_JSON_CONTENT_TYPE)
                                .withFault(Fault.CONNECTION_RESET_BY_PEER)
                        )
        );

        Throwable throwable = catchThrowable(() -> winningNumbersGenerable.generateSixWinningNumbers(1, 99, 25));

        assertAll(
                () -> assertThat(throwable).isInstanceOf(ResponseStatusException.class),
                () -> assertThat(throwable.getMessage()).isEqualTo("500 INTERNAL_SERVER_ERROR")
        );
    }

    @Test
    void should_throw_exception_when_fault_EMPTY_RESPONSE() {

        wireMockServer.stubFor(WireMock.get("/api/v1.0/random?min=1&max=99&count=25")
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader(CONTENT_TYPE, APPLICATION_JSON_CONTENT_TYPE)
                        .withFault(Fault.EMPTY_RESPONSE)
                )
        );

        Throwable throwable = catchThrowable(() -> winningNumbersGenerable.generateSixWinningNumbers(1, 99, 25));

        assertAll(
                () -> assertThat(throwable).isInstanceOf(ResponseStatusException.class),
                () -> assertThat(throwable.getMessage()).isEqualTo("500 INTERNAL_SERVER_ERROR")
        );
    }

    @Test
    void should_throw_exception_when_MALFORMED_RESPONSE_CHUNK_response() {

        wireMockServer.stubFor(WireMock.get("/api/v1.0/random?min=1&max=99&count=25")
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader(CONTENT_TYPE, APPLICATION_JSON_CONTENT_TYPE)
                        .withFault(Fault.MALFORMED_RESPONSE_CHUNK)
                )
        );

        Throwable throwable = catchThrowable(() -> winningNumbersGenerable.generateSixWinningNumbers(1, 99, 25));

        assertAll(
                () -> assertThat(throwable).isInstanceOf(ResponseStatusException.class),
                () -> assertThat(throwable.getMessage()).isEqualTo("500 INTERNAL_SERVER_ERROR")
        );
    }

    @Test
    void should_throw_exception_when_RANDOM_DATA_THEN_CLOSE_response() {

        wireMockServer.stubFor(WireMock.get("/api/v1.0/random?min=1&max=99&count=25")
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader(CONTENT_TYPE, APPLICATION_JSON_CONTENT_TYPE)
                        .withFault(Fault.RANDOM_DATA_THEN_CLOSE)
                )
        );

        Throwable throwable = catchThrowable(() -> winningNumbersGenerable.generateSixWinningNumbers(1, 99, 25));

        assertAll(
                () -> assertThat(throwable).isInstanceOf(IllegalArgumentException.class)
        );
    }


}
