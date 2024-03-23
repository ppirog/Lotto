package org.lotto.infrastructure.jwt;


import lombok.AllArgsConstructor;
import org.lotto.domain.loginandregister.LoginAndRegisterFacade;
import org.lotto.domain.loginandregister.dto.UserResponseDto;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collections;

@AllArgsConstructor
public class LoginUserDetailsService implements UserDetailsService {

    private final LoginAndRegisterFacade loginAndRegisterFacade;
    @Override
    public UserDetails loadUserByUsername(final String username) throws BadCredentialsException {
        final UserResponseDto username1 = loginAndRegisterFacade.findByUsername(username);
        return getUser(username1);
    }

    private org.springframework.security.core.userdetails.User getUser(UserResponseDto dto){
        return new org.springframework.security.core.userdetails.User(
                dto.username(),
                dto.password(),
                Collections.emptyList()
        );
    }
}
