package org.lotto;

import org.lotto.domain.numbergenerator.WinningNumberGeneratorFacadeConfigurationProperties;
import org.lotto.infrastructure.numbergenerator.http.WinningNumberGeneratorRestTemplateClientConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableConfigurationProperties({WinningNumberGeneratorFacadeConfigurationProperties.class, WinningNumberGeneratorRestTemplateClientConfigurationProperties.class})
@EnableScheduling
public class LottoSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(LottoSpringBootApplication.class, args);
    }
}

