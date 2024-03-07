package org.lotto.domain.numberreceiver;

public class NotFoundInDatabaseException extends RuntimeException{
    public NotFoundInDatabaseException(String message) {
        super(message);
    }
}
