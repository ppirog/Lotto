package org.lotto.domain.resultchecker;

import lombok.AllArgsConstructor;
import org.lotto.domain.numbergenerator.NumberGeneratorFacade;
import org.lotto.domain.numberreceiver.NumberReceiverFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
public class ResultCheckerConfiguration {

    @Bean
    public ResultCheckerFacade resultCheckerFacade(NumberReceiverFacade numberReceiverFacade, NumberGeneratorFacade numberGeneratorFacade,ResultCheckerConfigurationProperties properties) {
        return new ResultCheckerFacade(numberReceiverFacade, numberGeneratorFacade,properties);
    }

    public ResultCheckerFacade createForTest(NumberReceiverFacade numberReceiverFacade, NumberGeneratorFacade numberGeneratorFacade) {
        final ResultCheckerConfigurationProperties properties = ResultCheckerConfigurationProperties
                .builder()
                .minutesToWaitForResults(5)
                .build();

        return resultCheckerFacade(numberReceiverFacade, numberGeneratorFacade, properties);
    }
}
