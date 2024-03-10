package org.lotto.domain.numberannouncer;


import org.lotto.domain.numberreceiver.NumberReceiverFacade;
import org.lotto.domain.resultchecker.ResultCheckerFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class NumberAnnouncerConfiguration {

    @Bean
    public NumberAnnouncerFacade numberAnnouncerFacade(ResultRepository winningNumbersRepository, ResultCheckerFacade resultCheckerFacade, NumberReceiverFacade numberReceiverFacade) {
        return new NumberAnnouncerFacade(resultCheckerFacade, winningNumbersRepository,numberReceiverFacade);
    }
    
}
