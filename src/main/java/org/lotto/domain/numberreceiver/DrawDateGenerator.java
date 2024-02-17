package org.lotto.domain.numberreceiver;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

class DrawDateGenerator {

    public static LocalDateTime generateDrawDate(LocalDateTime currentDateTime) {

        if (currentDateTime.getDayOfWeek().equals(DayOfWeek.SATURDAY) && currentDateTime.getHour() < 12) {
            return currentDateTime.withHour(12).withMinute(0).withSecond(0).withNano(0);
        } else if (currentDateTime.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
            return currentDateTime.with(TemporalAdjusters.next(DayOfWeek.SATURDAY)).withHour(12).withMinute(0).withSecond(0).withNano(0);
        }

        LocalDateTime nextSaturday = currentDateTime.with(TemporalAdjusters.next(DayOfWeek.SATURDAY))
                .withHour(12).withMinute(0).withSecond(0).withNano(0);

        if (currentDateTime.isAfter(nextSaturday)) {
            nextSaturday = nextSaturday.plusWeeks(1);
        }

        return nextSaturday;
    }


}
