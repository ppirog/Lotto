package org.lotto.infrastructure.token.controller;

import lombok.Builder;

@Builder
public record JwtResponseDto(
        String token,
        String username
) {
}
