package org.lotto.domain.resultchecker;

import java.util.Set;
import lombok.Builder;

@Builder
record Player(
        String ticketId,
        Set<Integer> userNumbers,
        Set<Integer> winningNumbers,
        boolean isWinner,
        int howManyNumbersMatched

) {
}
