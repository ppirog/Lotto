package org.lotto.domain.numbergenerator;

import org.lotto.domain.numbergenerator.dto.OneRandomNumberResponseDto;

interface OneRandomNumberFetchable {
    OneRandomNumberResponseDto fetchOneRandomNumber(int lowerBound, int upperBound);
}
