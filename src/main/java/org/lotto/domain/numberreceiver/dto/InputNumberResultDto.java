package org.lotto.domain.numberreceiver.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record InputNumberResultDto(String message, LocalDateTime date, String ticketId) {
}
