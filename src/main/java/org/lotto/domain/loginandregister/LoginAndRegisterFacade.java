package org.lotto.domain.loginandregister;


import lombok.AllArgsConstructor;
import org.lotto.domain.loginandregister.dto.UserMessageDto;
import org.lotto.domain.loginandregister.dto.UserRequestDto;
import org.lotto.domain.loginandregister.dto.UserResponseDto;
import org.springframework.security.authentication.BadCredentialsException;


@AllArgsConstructor
public class LoginAndRegisterFacade {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserMessageDto register(UserRequestDto requestDto) {

        User user = userMapper.toUser(requestDto);

        if (userRepository.existsByUsername(user.username())) {
            return UserMessageDto.builder()
                    .userRequestDto(UserResponseDto.builder()
                            .username(user.username())
                            .password(user.password())
                            .build())
                    .message("User already exists")
                    .build();
        }

        userRepository.save(user);

        final UserResponseDto userResponseDto = userMapper.toUserResponseDto(user);
        return userMapper.toUserMessageDto(userResponseDto, "User registered");

    }

    public UserResponseDto findByUsername(String username) {
        final User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new BadCredentialsException("User not found"));

        return userMapper.toUserResponseDto(user);
    }
}
