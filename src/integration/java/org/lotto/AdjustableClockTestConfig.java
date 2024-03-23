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
class AdjustableClockTestConfig {

    @Bean
    @Primary
    public AdjustableClock clock() {
        return AdjustableClock.ofLocalDateAndLocalTime(
                LocalDate.of(2022, 11, 16),
                LocalTime.of(10, 0),
                ZoneId.systemDefault()
        );
    }



}
