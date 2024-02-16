package org.lotto.domain.numberreceiver;

import lombok.Builder;

import java.util.Set;

@Builder
class NumberValidator {

    private final Integer MIN_NUMBER = 1;
    private final Integer MAX_NUMBER = 99;
    private final Integer SIZE = 6;


    boolean validate(final Set<Integer> userNumbers) {
        return userNumbers
                .stream()
                .filter(number -> number >= MIN_NUMBER && number <= MAX_NUMBER)
                .distinct()
                .count() == SIZE;
    }
}
