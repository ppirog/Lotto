package org.lotto.domain.numberreceiver;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
@Builder
record Ticket (String id , Set<Integer> numbers, LocalDateTime date) {
}
