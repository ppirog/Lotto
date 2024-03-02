package org.lotto;

import org.lotto.domain.numbergenerator.WinningNumberGeneratorFacadeConfigurationProperties;
import org.lotto.infrastructure.numbergenerator.http.WinningNumberGeneratorRestTemplateClientConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({WinningNumberGeneratorFacadeConfigurationProperties.class, WinningNumberGeneratorRestTemplateClientConfigurationProperties.class})
public class LottoSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(LottoSpringBootApplication.class, args);
    }
}

