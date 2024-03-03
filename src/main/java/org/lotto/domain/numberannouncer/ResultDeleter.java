package org.lotto.domain.numberannouncer;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@AllArgsConstructor
class ResultDeleter {

    private final ResultRepository resultRepository;
    private final LocalDateTime date;

    void cleanUpOldResults() {
        final List<Result> resultsOlderThanOneMonth = resultRepository.findResultsOlderThanOneMonth(date);

        resultsOlderThanOneMonth
                .forEach(result -> resultRepository.deleteByTicketId(result.ticketId()));
    }

}
