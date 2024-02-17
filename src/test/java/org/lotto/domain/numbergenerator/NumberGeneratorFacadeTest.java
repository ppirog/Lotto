package org.lotto.domain.numbergenerator;

import org.junit.jupiter.api.Test;
import org.lotto.domain.numberreceiver.NumberReceiverFacade;

import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NumberGeneratorFacadeTest {

    NumberReceiverFacade numberReceiverFacade = mock(NumberReceiverFacade.class);

    @Test
    void should_return_winning_numbers() {
        // Given
        NumberGeneratorFacade numberGeneratorFacade = new NumberGeneratorFacade(
                new WinningNumbersGenerator(),
                numberReceiverFacade
        );
        // When
        var winningNumbers = numberGeneratorFacade.winningNumbers();
        // Then
        assertEquals(6, winningNumbers.numbers().size());
    }

    @Test
    void should_return_winning_numbers_with_correct_range() {
        // Given
        NumberGeneratorFacade numberGeneratorFacade = new NumberGeneratorFacade(
                new WinningNumbersGenerator(),
                numberReceiverFacade
        );
        // When
        var winningNumbers = numberGeneratorFacade.winningNumbers();
        // Then
        assertTrue(winningNumbers.numbers().stream().allMatch(number -> number >= 1 && number <= 99));
    }

    @Test
    void should_return_winning_numbers_with_no_duplicates() {
        // Given
        NumberGeneratorFacade numberGeneratorFacade = new NumberGeneratorFacade(
                new WinningNumbersGenerator(),
                numberReceiverFacade
        );
        // When
        var winningNumbers = numberGeneratorFacade.winningNumbers();
        // Then
        assertEquals(winningNumbers.numbers().size(), winningNumbers.numbers().stream().distinct().count());
    }

    @Test
    void should_return_winning_numbers_with_correct_size() {
        // Given
        NumberGeneratorFacade numberGeneratorFacade = new NumberGeneratorFacade(
                new WinningNumbersGenerator(),
                numberReceiverFacade
        );
        int expectedSetSize = Set.of(1, 2, 3, 4, 5, 6).size();

        // When
        int winningNumbersSize = numberGeneratorFacade.winningNumbers().numbers().size();
        // Then
        assertEquals(expectedSetSize, winningNumbersSize);
    }


    //mockito test
    @Test
    void should_return_winning_numbers_with_correct_date() {
        when(numberReceiverFacade.retrieveNextDrawDate(any())).thenReturn(LocalDateTime.of(2024, 2, 17, 12, 0));
        // Given
        NumberGeneratorFacade numberGeneratorFacade = new NumberGeneratorFacade(
                new WinningNumbersGenerator(),
                numberReceiverFacade
        );
        LocalDateTime expectedDate = LocalDateTime.of(2024, 2, 17, 12, 0);
        // When
        var winningNumbers = numberGeneratorFacade.winningNumbers();
        // Then
        assertThat(winningNumbers.drawDate()).isEqualTo(expectedDate);
    }

}