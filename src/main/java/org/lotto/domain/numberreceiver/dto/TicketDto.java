package org.lotto.domain.numberreceiver.dto;

import lombok.Builder;

import java.time.LocalDateTime;

import java.util.Set;
@Builder
public record TicketDto(
        LocalDateTime ticketOrderDate,
        LocalDateTime drawDate,
        String ticketId,
        Set<Integer> userNumbers
) {
}
