package org.lotto.domain.numberreceiver;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Repository
interface TicketRepository extends MongoRepository<Ticket, String> {
    Ticket save(Ticket ticket);

    List<Ticket> findByDate(LocalDateTime date);

    Optional<Ticket> findById(String ticketId);
}
