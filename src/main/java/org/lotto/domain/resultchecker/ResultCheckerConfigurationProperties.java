package org.lotto.domain.resultchecker;

import lombok.Builder;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Builder
@ConfigurationProperties(prefix = "lotto.result-checker.facade")
public record ResultCheckerConfigurationProperties(
        int minutesToWaitForResults
) {
}
