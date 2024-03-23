package org.lotto.domain.loginandregister;


import lombok.AllArgsConstructor;
import org.lotto.domain.loginandregister.dto.UserMessageDto;
import org.lotto.domain.loginandregister.dto.UserRequestDto;
import org.lotto.domain.loginandregister.dto.UserResponseDto;

@AllArgsConstructor
class UserMapper {
    private final IdGenerable idGenerable;

    public User toUser(UserRequestDto userRequestDto) {
        return User.builder()
                .id(idGenerable.generate())
                .username(userRequestDto.username())
                .password(userRequestDto.password())
                .build();
    }

    public UserResponseDto toUserResponseDto(User user) {
        return UserResponseDto.builder()
                .username(user.username())
                .password(user.password())
                .build();
    }

    public UserMessageDto toUserMessageDto(UserResponseDto userResponseDto, String message) {
        return UserMessageDto.builder()
                .userRequestDto(userResponseDto)
                .message(message)
                .build();
    }

}
