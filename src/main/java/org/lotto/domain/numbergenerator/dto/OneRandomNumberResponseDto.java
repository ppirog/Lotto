package org.lotto.domain.numbergenerator.dto;

import lombok.Builder;

@Builder
public record OneRandomNumberResponseDto(
        Integer number
) {
}
