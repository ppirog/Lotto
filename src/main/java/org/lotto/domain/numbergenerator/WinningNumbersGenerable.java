package org.lotto.domain.numbergenerator;

import org.lotto.domain.numbergenerator.dto.SixRandomNumbersDto;

public interface WinningNumbersGenerable {

    SixRandomNumbersDto generateSixWinningNumbers(final int lowerBand, final int upperBand, final int count);
}
