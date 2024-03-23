package org.lotto.domain.loginandregister.dto;

import lombok.Builder;

@Builder
public record UserResponseDto(
        String username,
        String password
) {
}
