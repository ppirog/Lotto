package org.lotto;

import org.lotto.domain.numberreceiver.AdjustableClock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

@Configuration
@Profile("integration")
class IntegrationConfiguration {

    @Bean
    @Primary
    public AdjustableClock clock() {
        return AdjustableClock.ofLocalDateAndLocalTime(
                LocalDate.of(2024, 2, 23),
                LocalTime.of(5, 0),
                ZoneId.systemDefault()
        );
    }
}
