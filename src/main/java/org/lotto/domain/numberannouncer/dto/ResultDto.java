package org.lotto.domain.numberannouncer.dto;

import lombok.Builder;

import java.io.Serializable;
import java.util.Set;
@Builder
public record ResultDto(
        String ticketId,
        boolean isWinner,
        Integer howManyNumbersWin,
        Set<Integer> winNumbers
) implements Serializable {
}
