package org.lotto.domain.numbergenerator;

import java.util.HashSet;
import java.util.Set;
class WinningNumbersGenerator implements WinningNumbersGenerable{

    public Set<Integer> generateSixWinningNumbers() {
        Set<Integer> winIntegers = new HashSet<>();

        while(winIntegers.size() < 6) {
            winIntegers.add((int) (Math.random() * 99) + 1);
        }

        return winIntegers;
    }


}
