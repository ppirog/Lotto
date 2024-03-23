package org.lotto.infrastructure.token.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.lotto.infrastructure.jwt.JwtAuthenticatorFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
@AllArgsConstructor
class TokenRestController {

    private final JwtAuthenticatorFacade jwtAuthenticatorFacade;

    @PostMapping
    public ResponseEntity<JwtResponseDto> authenticateAndGenerateToken(@Valid @RequestBody TokenRequestDto tokenRequestDto){
        return ResponseEntity.ok(jwtAuthenticatorFacade.authenticateAndGenerateToken(tokenRequestDto));
    }

}
