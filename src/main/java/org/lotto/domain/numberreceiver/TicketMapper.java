package org.lotto.domain.numberreceiver;

import org.lotto.domain.numberreceiver.dto.TicketDto;

class TicketMapper {

    public static TicketDto mapFromTicketToTicketDto(Ticket ticket){
        return TicketDto.builder()
                .date(ticket.date())
                .ticketId(ticket.id())
                .userNumbers(ticket.numbers())
                .build();
    }
}
