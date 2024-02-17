package org.lotto.domain.numbergenerator;

import java.util.Set;

class WinningNumbersGeneratorTestImplWithIncorrectRangeOfNumbersInSetOfNumbers implements WinningNumbersGenerable{
    @Override
    public Set<Integer> generateSixWinningNumbers() {
        return Set.of(1, 2, 3, 4, 5, 100);
    }
}
