package org.lotto.domain.numberannouncer;

import org.lotto.domain.numberannouncer.dto.ResultDto;
import org.lotto.domain.resultchecker.dto.PlayerDto;

class ResultMapper {

    public static Result mapToResultFromPlayerDto(PlayerDto playerDto){
        return Result.builder()
                .ticketId(playerDto.ticketId())
                .isWinner(playerDto.isWinner())
                .howManyNumbersWin(playerDto.howManyNumbersWin())
                .winNumbers(playerDto.winNumbers())
                .drawDate(playerDto.drawDate())
                .build();
    }

    public static ResultDto mapToPlayerDtoFromResult(Result result){
        return ResultDto.builder()
                .ticketId(result.ticketId())
                .isWinner(result.isWinner())
                .howManyNumbersWin(result.howManyNumbersWin())
                .winNumbers(result.winNumbers())
                .build();
    }
}
