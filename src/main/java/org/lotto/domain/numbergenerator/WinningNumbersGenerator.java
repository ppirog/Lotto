package org.lotto.domain.numbergenerator;

import lombok.AllArgsConstructor;
import org.lotto.domain.numbergenerator.dto.OneRandomNumberResponseDto;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
class WinningNumbersGenerator implements WinningNumbersGenerable{
    private static final Integer UPPER_BOUND = 99;
    private static final Integer LOWER_BOUND = 1;

    private final OneRandomNumberFetchable oneRandomNumberFetcher;

    @Override
    public Set<Integer> generateSixWinningNumbers() {

        Set<Integer> winIntegers = new HashSet<>();

        while(winIntegers.size() < 6) {
            final OneRandomNumberResponseDto oneRandomNumberResponseDto = oneRandomNumberFetcher.fetchOneRandomNumber(LOWER_BOUND, UPPER_BOUND);
            winIntegers.add(oneRandomNumberResponseDto.number());
        }
        return winIntegers;
    }
}
