package org.lotto.domain.numberannouncer;

import lombok.AllArgsConstructor;

import java.util.List;
@AllArgsConstructor
class ResultDeleter {

    private final ResultRepository resultRepository;

    void cleanUpOldResults() {
        final List<Result> resultsOlderThanOneMonth = resultRepository.findResultsOlderThanOneMonth();

        resultsOlderThanOneMonth
                .forEach(result -> resultRepository.deleteByTicketId(result.ticketId()));
    }

}
