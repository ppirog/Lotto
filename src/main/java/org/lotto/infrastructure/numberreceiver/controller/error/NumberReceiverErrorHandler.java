package org.lotto.infrastructure.numberreceiver.controller.error;

import lombok.extern.log4j.Log4j2;
import org.lotto.domain.numberreceiver.NotFoundInDatabaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Log4j2
public class NumberReceiverErrorHandler {

    @ExceptionHandler(NotFoundInDatabaseException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public NotFoundTicketResponseDto handleException(NotFoundInDatabaseException exception) {
        log.warn("NotFoundTicketException - error while accesing ticket by id");
        return new NotFoundTicketResponseDto(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
