package org.lotto.domain.numberreceiver;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class InMemoryNumberReceiverRepositoryTestImpl implements NumberReceiverRepository {

    private final Map<String, Ticket> inMemoryDatabase = new ConcurrentHashMap<>();

    @Override
    public Ticket save(final Ticket ticket) {
        return inMemoryDatabase.put(ticket.id(), ticket);
    }

    @Override
    public List<Ticket> findAll(final LocalDateTime date) {
        return inMemoryDatabase
                .values()
                .stream()
                .filter(ticket -> ticket.date().toLocalDate().equals(date.toLocalDate()))
                .toList();
    }
}
