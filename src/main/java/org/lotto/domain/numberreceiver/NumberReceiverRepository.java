package org.lotto.domain.numberreceiver;

import java.time.LocalDateTime;
import java.util.List;

interface NumberReceiverRepository {
    Ticket save(Ticket ticket);

    List<Ticket> findAll(LocalDateTime date);
}
