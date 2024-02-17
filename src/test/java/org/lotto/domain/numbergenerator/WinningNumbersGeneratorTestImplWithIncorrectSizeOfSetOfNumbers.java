package org.lotto.domain.numbergenerator;

import java.util.Set;
class WinningNumbersGeneratorTestImplWithIncorrectSizeOfSetOfNumbers implements WinningNumbersGenerable{
    @Override
    public Set<Integer> generateSixWinningNumbers() {
        return Set.of(1, 2, 3, 4, 5);
    }
}
