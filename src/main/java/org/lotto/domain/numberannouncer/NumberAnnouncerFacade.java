package org.lotto.domain.numberannouncer;

import lombok.AllArgsConstructor;
import org.lotto.domain.numberannouncer.dto.ResultDto;
import org.lotto.domain.resultchecker.ResultCheckerFacade;
import org.lotto.domain.resultchecker.dto.PlayerDto;

@AllArgsConstructor
public class NumberAnnouncerFacade {

    private final ResultCheckerFacade resultCheckerFacade;
    private final ResultRepository resultRepository;

    public ResultDto announceResult(String ticketId) {

        if (resultRepository.existsByTicketId(ticketId)) {

            final Result byTicketId = resultRepository.findByTicketId(ticketId)
                    .orElseThrow(() -> new NotFoundInDatabaseException("Result should exist"));

            cleanUpOldResults();

            return ResultMapper.mapToPlayerDtoFromResult(byTicketId);
        }

        final PlayerDto byId = resultCheckerFacade.findById(ticketId);

        Result result = ResultMapper.mapToResultFromPlayerDto(byId);


        resultRepository.save(result);

        cleanUpOldResults();

        return ResultMapper.mapToPlayerDtoFromResult(result);
    }

    private void cleanUpOldResults() {
        ResultDeleter resultDeleter = new ResultDeleter(resultRepository);
        resultDeleter.cleanUpOldResults();
    }
}
