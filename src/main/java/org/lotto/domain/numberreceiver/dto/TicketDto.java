package org.lotto.domain.numberreceiver.dto;

import lombok.Builder;

import java.time.LocalDateTime;

import java.util.Set;
@Builder
public record TicketDto(LocalDateTime date, String ticketId,Set<Integer> userNumbers) {
}
