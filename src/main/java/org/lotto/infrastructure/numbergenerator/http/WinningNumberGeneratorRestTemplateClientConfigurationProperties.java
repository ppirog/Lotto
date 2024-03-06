package org.lotto.infrastructure.numbergenerator.http;

import lombok.Builder;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Builder
@ConfigurationProperties(prefix = "lotto.number-generator.http.client.config")
public record WinningNumberGeneratorRestTemplateClientConfigurationProperties(
        String uri,
        int port,
        int connectionTimeout,
        int readTimeout
) {
}