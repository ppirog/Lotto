package org.lotto.domain.numbergenerator;

class NotFoundInDatabaseException extends RuntimeException{
    public NotFoundInDatabaseException(String message) {
        super(message);
    }
}
