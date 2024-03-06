package org.lotto.infrastructure.numbergenerator.http;

import org.lotto.domain.numbergenerator.WinningNumberGeneratorFacadeConfigurationProperties;
import org.lotto.domain.numbergenerator.WinningNumbersGenerable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RandomGeneratorClientConfig {

    @Bean
    public RestTemplateResponseErrorHandler restTemplateResponseErrorHandler() {
        return new RestTemplateResponseErrorHandler();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateResponseErrorHandler restTemplateResponseErrorHandler, WinningNumberGeneratorRestTemplateClientConfigurationProperties properties) {
        return new RestTemplateBuilder()
                .errorHandler(restTemplateResponseErrorHandler)
                .setConnectTimeout(Duration.ofMillis(properties.connectionTimeout()))
                .setReadTimeout(Duration.ofMillis(properties.readTimeout()))
                .build();
    }

    @Bean
    public WinningNumbersGenerable remoteNumberGeneratorClient(RestTemplate restTemplate, WinningNumberGeneratorRestTemplateClientConfigurationProperties properties) {
        return new WinningNumbersGenerableRestTemplate(restTemplate, properties.uri(), properties.port());
    }

}
