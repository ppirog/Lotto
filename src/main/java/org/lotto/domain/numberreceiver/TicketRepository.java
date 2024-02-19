package org.lotto.domain.numberreceiver;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

interface TicketRepository {
    Ticket save(Ticket ticket);

    List<Ticket> findAll(LocalDateTime date);

    Optional<Ticket> findById(String ticketId);
}
