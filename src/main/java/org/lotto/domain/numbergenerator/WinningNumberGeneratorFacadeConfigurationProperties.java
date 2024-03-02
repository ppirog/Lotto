package org.lotto.domain.numbergenerator;

import lombok.Builder;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Builder
@ConfigurationProperties(prefix = "lotto.number-generator.facade")
public record WinningNumberGeneratorFacadeConfigurationProperties(
        int minimalWinningNumber,
        int maximalWinningNumber,
        int count
) {
}
