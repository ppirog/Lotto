package org.lotto.infrastructure.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
@ConfigurationProperties(value = "auth.jwt")
public record JwtConfigurationProperties(
        String secret,
        Long expirationTime
) {
}
