package org.lotto.domain.resultchecker;

import org.lotto.domain.numbergenerator.dto.WinningNumbersDto;
import org.lotto.domain.numberreceiver.dto.TicketDto;
import org.lotto.domain.resultchecker.dto.PlayerDto;
class PlayerMapper {


    public static PlayerDto mapFromTicketDtoAndWinningNumbersDtoToPlayerDto(TicketDto ticketDto, WinningNumbersDto winningNumbersDto,TicketValidator ticketValidator){
        return PlayerDto.builder()
                .ticketId(ticketDto.ticketId())
                .numbers(ticketDto.userNumbers())
                .winNumbers(ticketValidator.winningUserNumbers(ticketDto.userNumbers(), winningNumbersDto.numbers()))
                .howManyNumbersWin(ticketValidator.howManyNumbersWin(ticketDto.userNumbers(), winningNumbersDto.numbers()))
                .isWinner(ticketValidator.isWinner(ticketDto.userNumbers(), winningNumbersDto.numbers()))
                .drawDate(ticketDto.drawDate())
                .build();

    }
}
