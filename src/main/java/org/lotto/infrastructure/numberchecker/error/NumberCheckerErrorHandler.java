package org.lotto.infrastructure.numberchecker.error;

import lombok.extern.log4j.Log4j2;
import org.lotto.domain.resultchecker.DrawDateIsAfterNowException;
import org.lotto.infrastructure.numberreceiver.controller.error.NotFoundTicketResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Log4j2
class NumberCheckerErrorHandler {

    @ExceptionHandler(DrawDateIsAfterNowException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.TOO_EARLY)
    public NotFoundTicketResponseDto handleException(DrawDateIsAfterNowException exception) {
        log.warn("DrawDateIsAfterNowException - error while accesing ticket by id");
        return new NotFoundTicketResponseDto(exception.getMessage(), HttpStatus.TOO_EARLY);
    }
}
