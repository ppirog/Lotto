package org.lotto.http.numbergenerator;

import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.lotto.domain.numbergenerator.WinningNumbersGenerable;
import org.lotto.infrastructure.numbergenerator.http.RandomGeneratorClientConfig;
import org.lotto.infrastructure.numbergenerator.http.RestTemplateResponseErrorHandler;
import org.lotto.infrastructure.numbergenerator.http.WinningNumberGeneratorRestTemplateClientConfigurationProperties;
import org.springframework.web.client.RestTemplate;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

class RandomNumberGeneratorRestTemplateIntegrationTestConfig extends RandomGeneratorClientConfig {
    @RegisterExtension
    public static WireMockExtension wireMockServer = WireMockExtension.newInstance()
            .options(wireMockConfig().dynamicPort())
            .build();

    public WinningNumbersGenerable remoteNumberGeneratorClient(RestTemplateResponseErrorHandler handler) {
        final WinningNumberGeneratorRestTemplateClientConfigurationProperties properties1 = new WinningNumberGeneratorRestTemplateClientConfigurationProperties(
                "http://localhost",wireMockServer.getPort(),1000,1000
        );
        final RestTemplate restTemplate = restTemplate(handler, properties1);
        return remoteNumberGeneratorClient(restTemplate, properties1);
    }


}
