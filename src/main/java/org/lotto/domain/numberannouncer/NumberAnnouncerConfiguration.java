package org.lotto.domain.numberannouncer;


import org.lotto.domain.resultchecker.ResultCheckerFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class NumberAnnouncerConfiguration {

    @Bean
    public NumberAnnouncerFacade numberAnnouncerFacade(ResultRepository winningNumbersRepository, ResultCheckerFacade resultCheckerFacade) {
        return new NumberAnnouncerFacade(resultCheckerFacade, winningNumbersRepository);
    }
    
}
