package org.lotto.domain.resultchecker;

import java.util.Set;
import lombok.AllArgsConstructor;
import org.lotto.domain.numbergenerator.NumberGeneratorFacade;
import org.lotto.domain.numberreceiver.NumberReceiverFacade;
import org.lotto.domain.resultchecker.dto.PlayerDto;

/**
 *  * klient dostaje informacje o swoim unikalnym ID losownaia
 *  * klient moze sprawdzic czy wygrał (informacja ile trafił liczb)
 * */
@AllArgsConstructor
public class ResultCheckerFacade {

    private final NumberReceiverFacade numberReceiverFacade;
    private final NumberGeneratorFacade numberGeneratorFacade;

    public PlayerDto findById(String ticketId){





        return PlayerDto.builder()
                .ticketId("123")
                .numbers(Set.of(1,2,3,4,5,6))
                .winNumbers(Set.of(1,2,3,4,5,6))
                .howManyNumbersWin(3)
                .build();

    }


}
