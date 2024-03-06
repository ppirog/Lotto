package org.lotto.domain.numberreceiver;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
@Builder
@Document
record Ticket (
        @Id
        String id ,
        Set<Integer> numbers,
        LocalDateTime date) {
}
