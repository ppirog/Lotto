package org.lotto.domain.numberannouncer;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
record Result(
        String ticketId,
        boolean isWinner,
        Integer howManyNumbersWin,
        Set<Integer> winNumbers,
        LocalDateTime drawDate
) {
}
