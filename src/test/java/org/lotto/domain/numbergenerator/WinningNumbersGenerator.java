package org.lotto.domain.numbergenerator;

import lombok.AllArgsConstructor;
import org.lotto.domain.numbergenerator.dto.SixRandomNumbersDto;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@AllArgsConstructor
class WinningNumbersGenerator implements WinningNumbersGenerable {
    private static final Integer UPPER_BOUND = 99;
    private static final Integer LOWER_BOUND = 1;

    @Override
    public SixRandomNumbersDto generateSixWinningNumbers() {
        Set<Integer> winIntegers = new HashSet<>();

        while (winIntegers.size() < 6) {
            Random random = new SecureRandom();

            Integer randomInt = random.nextInt(UPPER_BOUND - LOWER_BOUND) + 1;
            winIntegers.add(randomInt);
        }
        return SixRandomNumbersDto.builder().numbers(winIntegers).build();
    }
}
