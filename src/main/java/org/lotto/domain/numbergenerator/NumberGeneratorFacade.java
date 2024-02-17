package org.lotto.domain.numbergenerator;

import lombok.AllArgsConstructor;
import org.lotto.domain.numbergenerator.dto.WinningNumbersDto;
import org.lotto.domain.numberreceiver.NumberReceiverFacade;

import java.time.LocalDateTime;

@AllArgsConstructor
public class NumberGeneratorFacade {

    private final WinningNumbersGenerable winningNumbersGenerator;
    private final NumberReceiverFacade numberReceiverFacade;
    public WinningNumbersDto winningNumbers(){
        return WinningNumbersDto.builder()
                .numbers(winningNumbersGenerator.generateSixWinningNumbers())
                .drawDate(numberReceiverFacade.retrieveNextDrawDate(LocalDateTime.now()))
                .build();
    }


}
