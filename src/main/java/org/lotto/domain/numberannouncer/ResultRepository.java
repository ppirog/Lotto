package org.lotto.domain.numberannouncer;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

interface ResultRepository {

    Result save(Result result);

    Optional<Result> findByTicketId(String ticketId);

    boolean existsByTicketId(String ticketId);

    Optional<Result> deleteByTicketId(String ticketId);

    List<Result> findResultsOlderThanOneMonth(LocalDateTime date);

    List<Result> findAll();
}
