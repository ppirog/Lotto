package org.lotto.domain.resultchecker;

public class DrawDateIsAfterNowException extends RuntimeException {
    DrawDateIsAfterNowException(String message) {
        super(message);
    }
}
