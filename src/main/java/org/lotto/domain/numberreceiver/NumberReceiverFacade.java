package org.lotto.domain.numberreceiver;

import lombok.AllArgsConstructor;
import org.lotto.domain.numberreceiver.dto.InputNumberResultDto;
import org.lotto.domain.numberreceiver.dto.TicketDto;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * klinet podaje 6 liczb z zakresu 1-99
 * liczby nie mogą się powtarzać
 * klient dostaje informajce o dacie losowania
 * klient dostaje informacje o swoim unikalnym ID losownaia
 */
@AllArgsConstructor
public class NumberReceiverFacade {

    private final NumberValidator numberValidator;
    private final NumberReceiverRepository repository;
    private final Clock clock;

    public InputNumberResultDto inputNumbers(Set<Integer> numbersFromUser) {

        final boolean isCorrectInputSize = numberValidator.validate(numbersFromUser);

        if (!isCorrectInputSize) {
            return InputNumberResultDto.builder()
                    .message("failure")
                    .build();
        }

        final String id = UUID.randomUUID().toString();
        LocalDateTime now = LocalDateTime.now(clock);

        Ticket ticket = Ticket.builder()
                .id(id)
                .numbers(numbersFromUser)
                .date(now)
                .build();

        repository.save(ticket);

        return InputNumberResultDto.builder()
                .ticketId(ticket.id())
                .date(ticket.date())
                .message("success")
                .build();
    }


    public List<TicketDto> userNumbers(LocalDateTime date) {

        final List<Ticket> tickets = repository.findAll(date);
        return tickets.stream()
                .map(TicketMapper::mapFromTicketToTicketDto)
                .toList();
    }
}
