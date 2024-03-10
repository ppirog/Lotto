package org.lotto.domain.numberreceiver;

import lombok.AllArgsConstructor;
import org.lotto.domain.numberreceiver.dto.InputNumberResultDto;
import org.lotto.domain.numberreceiver.dto.TicketDto;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * klinet podaje 6 liczb z zakresu 1-99
 * liczby nie mogą się powtarzać
 * klient dostaje informajce o dacie losowania (losowania są co soboty o 12:00)
 * klient dostaje informacje o swoim unikalnym ID losownaia
 * klient moze sprawdzic czy wygrał (informacja ile trafił liczb)
 */
@AllArgsConstructor
public class NumberReceiverFacade {

    private final NumberValidator numberValidator;
    private final TicketRepository repository;
    private final Clock clock;
    private final IdGenerable idGenerator;

    public InputNumberResultDto inputNumbers(Set<Integer> numbersFromUser) {

        final boolean isCorrectInputSize = numberValidator.validate(numbersFromUser);

        if (!isCorrectInputSize) {
            return InputNumberResultDto.builder()
                    .message("failure")
                    .build();
        }

        final String id = idGenerator.generateId();
        LocalDateTime now = LocalDateTime.now(clock);

        Ticket ticket = Ticket.builder()
                .id(id)
                .numbers(numbersFromUser)
                .date(now)
                .build();

        repository.save(ticket);

        return InputNumberResultDto.builder()
                .ticketDto(TicketMapper.mapFromTicketToTicketDto(ticket))
                .message("success")
                .build();
    }


    public List<TicketDto> userNumbers(LocalDateTime date) {

        final List<Ticket> tickets = repository.findByDate(date);
        return tickets.stream()
                .map(TicketMapper::mapFromTicketToTicketDto)
                .toList();
    }

    public TicketDto usersNumberByTicketId(String ticketId) {

        final Ticket ticket = repository.findById(ticketId)
                .orElseThrow(() -> new NotFoundInDatabaseException("No ticket found for id: " + ticketId));

        return TicketMapper.mapFromTicketToTicketDto(ticket);
    }



    public LocalDateTime retrieveNextDrawDate() {
        return DrawDateGenerator.generateDrawDate(LocalDateTime.now(clock));
    }

    public LocalDateTime getNow() {
        return LocalDateTime.now(clock);
    }
}
