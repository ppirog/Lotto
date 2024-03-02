package org.lotto.infrastructure.numbergenerator.scheduler;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.lotto.domain.numbergenerator.NumberGeneratorFacade;
import org.lotto.domain.numbergenerator.dto.WinningNumbersDto;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Log4j2
public class WinninigNumberScheduler {
    private final NumberGeneratorFacade numberGeneratorFacade;


    @Scheduled(cron = "${lotto.number-generator.scheduler.cron}")
    public void f() {
        log.info("Generating winning numbers");
        final WinningNumbersDto winningNumbersDto = numberGeneratorFacade.generateWinningNumbers();
        log.info("Winning numbers generated: " + winningNumbersDto);
    }
}
