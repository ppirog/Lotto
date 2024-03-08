package org.lotto.infrastructure.numberreceiver.controller.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Builder
public record InputNumberRequestDto(
        @NotNull(message = "Numbers cannot be null")
        @NotEmpty(message = "Numbers cannot be empty")
        List<Integer> numbers
) {
}
