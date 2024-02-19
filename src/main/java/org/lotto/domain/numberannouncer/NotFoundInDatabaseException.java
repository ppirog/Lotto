package org.lotto.domain.numberannouncer;

class NotFoundInDatabaseException extends RuntimeException {
    public NotFoundInDatabaseException(String message) {
        super(message);
    }
}
