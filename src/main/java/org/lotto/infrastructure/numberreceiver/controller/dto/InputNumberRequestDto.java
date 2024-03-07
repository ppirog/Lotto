package org.lotto.infrastructure.numberreceiver.controller.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record InputNumberRequestDto(
        List<Integer> numbers
) {
}
