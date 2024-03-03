package org.lotto.domain.numberannouncer;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

class InMemoryResultRepositoryTestImpl implements ResultRepository {

    private final Map<String, Result> results = new ConcurrentHashMap<>();

    @Override
    public Result save(final Result result) {
        results.put(result.ticketId(), result);
        return result;
    }

    @Override
    public Optional<Result> findByTicketId(final String ticketId) {
        return Optional.ofNullable(results.get(ticketId));
    }

    @Override
    public boolean existsByTicketId(final String ticketId) {
        return results.containsKey(ticketId);
    }

    @Override
    public Optional<Result> deleteByTicketId(final String ticketId) {
        return Optional.ofNullable(results.remove(ticketId));
    }

    @Override
    public List<Result> findResultsOlderThanOneMonth(LocalDateTime date) {
        return results.values().stream().filter(result -> result.drawDate().isBefore(date.minusMonths(1))).toList();

    }

    @Override
    public List<Result> findAll() {
        return results.values().stream().toList();
    }
}
