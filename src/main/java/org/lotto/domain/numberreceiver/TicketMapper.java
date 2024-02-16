package org.lotto.domain.numberreceiver;

import lombok.AllArgsConstructor;
import org.lotto.domain.numberreceiver.dto.TicketDto;
@AllArgsConstructor
class TicketMapper {


    public static TicketDto mapFromTicketToTicketDto(Ticket ticket){
        return TicketDto.builder()
                .ticketOrderDate(ticket.date())
                .drawDate(DrawDateGenerator.generateDrawDate(ticket.date()))
                .ticketId(ticket.id())
                .userNumbers(ticket.numbers())
                .build();
    }
}
