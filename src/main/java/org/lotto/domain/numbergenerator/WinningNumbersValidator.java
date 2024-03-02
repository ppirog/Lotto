package org.lotto.domain.numbergenerator;

import org.springframework.stereotype.Component;

import java.util.Set;
@Component
class WinningNumbersValidator {
    private final Integer MIN_NUMBER = 1;
    private final Integer MAX_NUMBER = 99;
    private final Integer SIZE = 6;


    boolean validate(final Set<Integer> userNumbers) {

        final boolean isCorrectSize = userNumbers
                .stream()
                .filter(number -> number >= MIN_NUMBER && number <= MAX_NUMBER)
                .distinct()
                .count() == SIZE;

        if(!isCorrectSize) {
            throw new IllegalStateException("Invalid numbers");
        }

        return isCorrectSize;
    }
}
