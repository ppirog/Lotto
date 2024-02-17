package org.lotto.domain.numberreceiver;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class DrawDateGeneratorTest {

    @Test
    void should_return_correct_draw_when_day_is_before_next_draw_date() {
        // Given
        LocalDateTime currentTime = DrawDateGenerator.generateDrawDate(LocalDateTime.of(2024, 2, 23, 12, 0, 0));
        // When
        LocalDateTime expectedTime = LocalDateTime.of(2024, 2, 24, 12, 0, 0);
        // Then
        assertThat(currentTime).isEqualTo(expectedTime);
    }

    @Test
    void should_return_correct_draw_when_day_is_equal_next_draw_date_and_is_before_noon() {
        // Given
        LocalDateTime currentTime = DrawDateGenerator.generateDrawDate(LocalDateTime.of(2024, 2, 24, 11, 59, 59));
        // When
        LocalDateTime expectedTime = LocalDateTime.of(2024, 2, 24, 12, 0, 0);
        // Then
        assertThat(currentTime).isEqualTo(expectedTime);
    }

    @Test
    void should_return_correct_draw_when_day_is_equal_next_draw_date_and_is_after_noon() {
        // Given
        LocalDateTime currentTime = DrawDateGenerator.generateDrawDate(LocalDateTime.of(2024, 2, 24, 12, 00, 01));
        // When
        LocalDateTime expectedTime = LocalDateTime.of(2024, 3, 2, 12, 0, 0);
        // Then
        assertThat(currentTime).isEqualTo(expectedTime);
    }
    @Test
    void should_return_correct_draw_when_day_is_after_next_draw_date() {
        // Given
        LocalDateTime currentTime = DrawDateGenerator.generateDrawDate(LocalDateTime.of(2024, 2, 25, 12, 0, 0));
        // When
        LocalDateTime expectedTime = LocalDateTime.of(2024, 3, 2, 12, 0, 0);
        // Then
        assertThat(currentTime).isEqualTo(expectedTime);
    }

}