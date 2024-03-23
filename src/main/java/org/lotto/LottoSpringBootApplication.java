package org.lotto;

import org.lotto.domain.numbergenerator.WinningNumberGeneratorFacadeConfigurationProperties;
import org.lotto.domain.resultchecker.ResultCheckerConfigurationProperties;
import org.lotto.infrastructure.jwt.JwtConfigurationProperties;
import org.lotto.infrastructure.numbergenerator.http.WinningNumberGeneratorRestTemplateClientConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableConfigurationProperties({WinningNumberGeneratorFacadeConfigurationProperties.class, WinningNumberGeneratorRestTemplateClientConfigurationProperties.class, ResultCheckerConfigurationProperties.class, JwtConfigurationProperties.class})
@EnableScheduling
@EnableMongoRepositories
public class LottoSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(LottoSpringBootApplication.class, args);
    }
}


