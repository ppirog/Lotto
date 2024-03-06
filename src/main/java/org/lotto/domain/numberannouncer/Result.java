package org.lotto.domain.numberannouncer;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Document
record Result(
        @Id
        String ticketId,
        boolean isWinner,
        Integer howManyNumbersWin,
        Set<Integer> winNumbers,
        LocalDateTime drawDate
) {
}
