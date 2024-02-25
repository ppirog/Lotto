package org.lotto.domain.numbergenerator;

import org.lotto.domain.numbergenerator.dto.SixRandomNumbersDto;

import java.util.Set;

class WinningNumbersGeneratorTestImplWithCorrectSetOfNumbers implements WinningNumbersGenerable{
    @Override
    public SixRandomNumbersDto generateSixWinningNumbers() {
        return SixRandomNumbersDto.builder()
                .numbers(Set.of(1, 2, 3, 4, 5, 6))
                .build();
    }
}
