package org.lotto.domain.numberannouncer;

import lombok.AllArgsConstructor;
import org.lotto.domain.numberannouncer.dto.ResultDto;
import org.lotto.domain.numberreceiver.NumberReceiverFacade;
import org.lotto.domain.resultchecker.ResultCheckerFacade;
import org.lotto.domain.resultchecker.dto.PlayerDto;
import org.springframework.cache.annotation.Cacheable;

import java.time.LocalDateTime;

@AllArgsConstructor
public class NumberAnnouncerFacade {

    private final ResultCheckerFacade resultCheckerFacade;
    private final ResultRepository resultRepository;
    private final NumberReceiverFacade numberReceiverFacade;

    @Cacheable("announceResult")
    public ResultDto announceResult(String ticketId) {

        if (resultRepository.existsByTicketId(ticketId)) {

            final Result byTicketId = resultRepository.findByTicketId(ticketId)
                    .orElseThrow(() -> new NotFoundInDatabaseException("Result should exist"));

            cleanUpOldResults(numberReceiverFacade.getCurrentLocalDateTime());

            return ResultMapper.mapToPlayerDtoFromResult(byTicketId);
        }

        final PlayerDto byId = resultCheckerFacade.findById(ticketId);

        Result result = ResultMapper.mapToResultFromPlayerDto(byId);


        resultRepository.save(result);

        cleanUpOldResults(numberReceiverFacade.getCurrentLocalDateTime());

        return ResultMapper.mapToPlayerDtoFromResult(result);
    }

    private void cleanUpOldResults(LocalDateTime date) {
        ResultDeleter resultDeleter = new ResultDeleter(resultRepository,date);
        resultDeleter.cleanUpOldResults();
    }
}
