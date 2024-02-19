package org.lotto.domain.numberreceiver;

class NotFoundInDatabaseException extends RuntimeException{
    public NotFoundInDatabaseException(String message) {
        super(message);
    }
}
