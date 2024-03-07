package org.lotto.domain.resultchecker;

import lombok.AllArgsConstructor;
import org.lotto.domain.numbergenerator.NumberGeneratorFacade;
import org.lotto.domain.numberreceiver.NumberReceiverFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@Configuration
class ResultCheckerConfiguration {

    @Bean
    public ResultCheckerFacade resultCheckerFacade(NumberReceiverFacade numberReceiverFacade, NumberGeneratorFacade numberGeneratorFacade) {
        return new ResultCheckerFacade(numberReceiverFacade, numberGeneratorFacade);
    }
}
