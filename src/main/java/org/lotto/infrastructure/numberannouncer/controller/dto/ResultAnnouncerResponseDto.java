package org.lotto.infrastructure.numberannouncer.controller.dto;

import lombok.Builder;
import java.util.Set;

@Builder
public record ResultAnnouncerResponseDto(
        String ticketId,
        boolean isWinner,
        Integer howManyNumbersWin,
        Set<Integer> winNumbers
) {
}
