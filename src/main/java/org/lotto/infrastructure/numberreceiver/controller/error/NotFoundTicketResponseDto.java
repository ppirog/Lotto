package org.lotto.infrastructure.numberreceiver.controller.error;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record NotFoundTicketResponseDto(
        String message,
        HttpStatus status
) {
}
