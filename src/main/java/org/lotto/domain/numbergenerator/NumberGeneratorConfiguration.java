package org.lotto.domain.numbergenerator;

import org.lotto.domain.numberreceiver.NumberReceiverFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Optional;

@Configuration
class NumberGeneratorConfiguration {
    @Bean
    public WinningNumbersRepository winningNumbersRepository() {
        return new WinningNumbersRepository() {
            @Override
            public WinningNumbers save(final WinningNumbers winningNumbers) {
                return null;
            }

            @Override
            public Optional<WinningNumbers> findByDate(final LocalDateTime date) {
                return Optional.empty();
            }

            @Override
            public boolean existsByDate(final LocalDateTime date) {
                return false;
            }
        };
    }
    @Bean
    NumberReceiverFacade numberReceiverFacade() {
        return new NumberReceiverFacade(null,null,null,null) {
            @Override
            public LocalDateTime retrieveNextDrawDate() {
                return null;
            }
        };
    }

    @Bean
    public NumberGeneratorFacade numberGeneratorFacade(WinningNumbersGenerable winningNumbersGenerable, WinningNumbersRepository winningNumbersRepository, NumberReceiverFacade numberReceiverFacade, WinningNumberGeneratorFacadeConfigurationProperties properties) {
        final WinningNumbersValidator winningNumbersValidator = new WinningNumbersValidator();
        return new NumberGeneratorFacade(winningNumbersGenerable, numberReceiverFacade, winningNumbersRepository, winningNumbersValidator, properties);
    }

    public NumberGeneratorFacade createForTest(WinningNumbersGenerable winningNumbersGenerable, WinningNumbersRepository winningNumbersRepository, NumberReceiverFacade numberReceiverFacade) {
        final WinningNumberGeneratorFacadeConfigurationProperties properties = WinningNumberGeneratorFacadeConfigurationProperties
                .builder()
                .minimalWinningNumber(1)
                .maximalWinningNumber(99)
                .count(6)
                .build();

        return numberGeneratorFacade(winningNumbersGenerable, winningNumbersRepository, numberReceiverFacade, properties);
    }

}
