package org.lotto.domain.numbergenerator;

import org.lotto.domain.numbergenerator.dto.OneRandomNumberResponseDto;

class OneRandomNumberFetcher implements OneRandomNumberFetchable{
    @Override
    public OneRandomNumberResponseDto fetchOneRandomNumber(final int lowerBound, final int upperBound) {
        int number = (int) (Math.random() * upperBound) + lowerBound;
        return OneRandomNumberResponseDto.builder()
                .number(number)
                .build();
    }
}
