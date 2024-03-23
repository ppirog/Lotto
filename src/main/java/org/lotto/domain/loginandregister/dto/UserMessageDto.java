package org.lotto.domain.loginandregister.dto;

import lombok.Builder;

@Builder
public record UserMessageDto(
        UserResponseDto userRequestDto,
        String message
) {
}
