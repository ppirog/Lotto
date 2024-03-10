package org.lotto.domain.resultchecker;

import lombok.AllArgsConstructor;
import org.lotto.domain.numbergenerator.NumberGeneratorFacade;
import org.lotto.domain.numbergenerator.dto.WinningNumbersDto;
import org.lotto.domain.numberreceiver.NumberReceiverFacade;
import org.lotto.domain.numberreceiver.dto.TicketDto;
import org.lotto.domain.resultchecker.dto.PlayerDto;

/**
 * * klient dostaje informacje o swoim unikalnym ID losownaia
 * * klient moze sprawdzic czy wygrał (informacja ile trafił liczb)
 */
@AllArgsConstructor
public class ResultCheckerFacade {

    private final NumberReceiverFacade numberReceiverFacade;
    private final NumberGeneratorFacade numberGeneratorFacade;
    private final ResultCheckerConfigurationProperties properties;

    public PlayerDto findById(String ticketId) {

        final TicketDto ticketDto = numberReceiverFacade.usersNumberByTicketId(ticketId);

        if (numberReceiverFacade.getCurrentLocalDateTime().isBefore(ticketDto.drawDate().plusMinutes(properties.minutesToWaitForResults()))) {
            throw new DrawDateIsAfterNowException("Draw date is after now");
        }

        final WinningNumbersDto winningNumbersDto = numberGeneratorFacade.retrieveWinningNumbers(ticketDto.drawDate());

        return PlayerMapper.mapFromTicketDtoAndWinningNumbersDtoToPlayerDto(ticketDto, winningNumbersDto, new TicketValidator());
    }

}