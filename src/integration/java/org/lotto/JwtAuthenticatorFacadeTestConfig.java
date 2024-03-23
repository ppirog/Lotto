package org.lotto;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.lotto.infrastructure.jwt.JwtAuthenticatorFacade;
import org.lotto.infrastructure.jwt.JwtConfigurationProperties;
import org.lotto.infrastructure.token.controller.JwtResponseDto;
import org.lotto.infrastructure.token.controller.TokenRequestDto;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Primary
@Component
@Profile("integration")
class JwtAuthenticatorFacadeTestConfig extends JwtAuthenticatorFacade {
    private final AuthenticationManager authenticatorManager;
    private final JwtConfigurationProperties properties;
    private final Clock clock;

    public JwtAuthenticatorFacadeTestConfig(final AuthenticationManager authenticatorManager, final JwtConfigurationProperties properties, final Clock clock) {
        super(authenticatorManager, properties, clock);
        this.authenticatorManager = authenticatorManager;
        this.properties = properties;
        this.clock = clock;
    }


    @Override
    public JwtResponseDto authenticateAndGenerateToken(final TokenRequestDto tokenRequestDto) {
        Authentication authenticate = authenticatorManager.authenticate(
                new UsernamePasswordAuthenticationToken(tokenRequestDto.username(), tokenRequestDto.password())
        );
        final User principal = (User) authenticate.getPrincipal();

        String token = createToken(principal);
        String username = principal.getUsername();

        return JwtResponseDto.builder()
                .username(username)
                .token(token)
                .build();
    }

    private String createToken(User user){
        String secretKey = properties.secret();
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        Instant now = LocalDateTime.now(clock).toInstant(ZoneOffset.UTC);
        String issuer = "Lotto Service";

        return JWT.create()
                .withSubject(user.getUsername())
                .withIssuedAt(now)
                .withIssuer(issuer)
                .sign(algorithm);
    }
}
