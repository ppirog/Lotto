package org.lotto.domain.numbergenerator;

import lombok.AllArgsConstructor;
import org.lotto.domain.numbergenerator.dto.WinningNumbersDto;
import org.lotto.domain.numberreceiver.NumberReceiverFacade;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
public class NumberGeneratorFacade {

    private final WinningNumbersGenerable winningNumbersGenerator;
    private final NumberReceiverFacade numberReceiverFacade;
    private final WinningNumbersRepository winningNumbersRepository;
    private final WinningNumbersValidator winningNumbersValidator;

    public WinningNumbersDto generateWinningNumbers() {

        final LocalDateTime nextDrawDate = numberReceiverFacade.retrieveNextDrawDate();

        if (!winningNumbersRepository.existsByDate(nextDrawDate)) {
            final Set<Integer> numbers = winningNumbersGenerator.generateSixWinningNumbers().numbers();
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
