package org.lotto.domain.resultchecker.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;
@Builder
public record PlayerDto(
        String ticketId,
        Set<Integer> numbers,
        Set<Integer> winNumbers,
        boolean isWinner,
        Integer howManyNumbersWin,
        LocalDateTime drawDate
) {
}
