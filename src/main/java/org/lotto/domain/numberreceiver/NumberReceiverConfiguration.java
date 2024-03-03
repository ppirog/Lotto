package org.lotto.domain.numberreceiver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Configuration
public class NumberReceiverConfiguration {
    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }

    @Bean
    public IdGenerable idGenerable() {
        return new IdGenerator();
    }




    @Bean
    public NumberReceiverFacade numberReceiverFacade(TicketRepository ticketRepository, Clock clock, IdGenerable idGenerable) {
        NumberValidator numberValidator = new NumberValidator();
        return new NumberReceiverFacade(numberValidator, ticketRepository, clock, idGenerable);
    }

    public NumberReceiverFacade createForTest(TicketRepository ticketRepository, Clock clock, IdGenerable idGenerable) {
        return numberReceiverFacade(ticketRepository, clock, idGenerable);
    }

}
