package org.lotto.domain.numbergenerator;

import lombok.AllArgsConstructor;
import org.lotto.domain.numbergenerator.dto.WinningNumbersDto;
import org.lotto.domain.numberreceiver.NumberReceiverFacade;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class NumberGeneratorFacade {

    private final WinningNumbersGenerable winningNumbersGenerator;
    private final NumberReceiverFacade numberReceiverFacade;
    private final WinningNumbersRepository winningNumbersRepository;
    private final WinningNumbersValidator winningNumbersValidator;
    private final WinningNumberGeneratorFacadeConfigurationProperties properties;

    public WinningNumbersDto generateWinningNumbers() {

        final LocalDateTime nextDrawDate = numberReceiverFacade.retrieveNextDrawDate();

        if (!winningNumbersRepository.existsByDate(nextDrawDate)) {
            Set<Integer> numbers = winningNumbersGenerator.generateSixWinningNumbers(properties.minimalWinningNumber(), properties.maximalWinningNumber(), properties.count()).numbers();
            numbers = numbers.stream()
                    .limit(6)
                    .collect(Collectors.toSet());

            winningNumbersValidator.validate(numbers);
            winningNumbersRepository.save(WinningNumbers.builder()
                    .numbers(numbers)
                    .date(nextDrawDate)
                    .build());
        }

        return retrieveWinningNumbers(nextDrawDate);
    }

    public WinningNumbersDto retrieveWinningNumbers(LocalDateTime drawDate) {

        final WinningNumbers winningNumbersFromDatabase = winningNumbersRepository.findByDate(drawDate)
                .orElseThrow(() -> new NotFoundInDatabaseException("No winning numbers found for date: " + drawDate));

        return WinningNumbersDto.builder()
                .numbers(winningNumbersFromDatabase.numbers())
                .drawDate(winningNumbersFromDatabase.date())
                .build();
    }


}
