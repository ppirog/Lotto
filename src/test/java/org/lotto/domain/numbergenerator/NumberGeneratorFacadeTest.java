package org.lotto.domain.numbergenerator;

import org.junit.jupiter.api.Test;
import org.lotto.domain.numberreceiver.NumberReceiverFacade;

import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NumberGeneratorFacadeTest {


    NumberReceiverFacade numberReceiverFacade = mock(NumberReceiverFacade.class);

    NumberGeneratorFacade numberGeneratorFacadeWithRandomNumbers = new NumberGeneratorFacade(new WinningNumbersGenerator(), numberReceiverFacade, new InMemoryWinningNumbersRepositoryTestImpl(), new WinningNumbersValidator());
    NumberGeneratorFacade numberGeneratorFacadeWithStaticNumbers = new NumberGeneratorFacade(new WinningNumbersGeneratorTestImplWithCorrectSetOfNumbers(), numberReceiverFacade, new InMemoryWinningNumbersRepositoryTestImpl(), new WinningNumbersValidator());

    NumberGeneratorFacade numberGeneratorFacadeWithStaticIncorrectRangeOfNumbers = new NumberGeneratorFacade(new WinningNumbersGeneratorTestImplWithIncorrectRangeOfNumbersInSetOfNumbers(), numberReceiverFacade, new InMemoryWinningNumbersRepositoryTestImpl(), new WinningNumbersValidator());
    NumberGeneratorFacade numberGeneratorFacadeWithStaticIncorrectSizeSetOfNumbers = new NumberGeneratorFacade(new WinningNumbersGeneratorTestImplWithIncorrectSizeOfSetOfNumbers(), numberReceiverFacade, new InMemoryWinningNumbersRepositoryTestImpl(), new WinningNumbersValidator());

    @Test
    void should_return_winning_numbers() {
        when(numberReceiverFacade.retrieveNextDrawDate()).thenReturn(LocalDateTime.of(2024, 2, 17, 12, 0));

        // When
        var winningNumbers = numberGeneratorFacadeWithRandomNumbers.generateWinningNumbers();
        // Then
        assertEquals(6, winningNumbers.numbers().size());
    }

    @Test
    void should_return_winning_numbers_with_correct_range() {
        when(numberReceiverFacade.retrieveNextDrawDate()).thenReturn(LocalDateTime.of(2024, 2, 17, 12, 0));

        // When
        var winningNumbers = numberGeneratorFacadeWithRandomNumbers.generateWinningNumbers();
        // Then
        assertTrue(winningNumbers.numbers().stream().allMatch(number -> number >= 1 && number <= 99));
    }

    @Test
    void should_return_winning_numbers_with_no_duplicates() {
        when(numberReceiverFacade.retrieveNextDrawDate()).thenReturn(LocalDateTime.of(2024, 2, 17, 12, 0));

        // When
        var winningNumbers = numberGeneratorFacadeWithRandomNumbers.generateWinningNumbers();
        // Then
        assertEquals(winningNumbers.numbers().size(), winningNumbers.numbers().stream().distinct().count());
    }

    @Test
    void should_return_winning_numbers_with_correct_size() {
        when(numberReceiverFacade.retrieveNextDrawDate()).thenReturn(LocalDateTime.of(2024, 2, 17, 12, 0));

        // Given
        int expectedSetSize = Set.of(1, 2, 3, 4, 5, 6).size();
        // When
        int winningNumbersSize = numberGeneratorFacadeWithRandomNumbers.generateWinningNumbers().numbers().size();
        // Then
        assertEquals(expectedSetSize, winningNumbersSize);
    }


    //mockito test
    @Test
    void should_return_winning_numbers_with_correct_date() {
        when(numberReceiverFacade.retrieveNextDrawDate()).thenReturn(LocalDateTime.of(2024, 2, 17, 12, 0));
        // Given

        LocalDateTime expectedDate = LocalDateTime.of(2024, 2, 17, 12, 0);
        // When
        var winningNumbers = numberGeneratorFacadeWithRandomNumbers.generateWinningNumbers();
        // Then
        assertThat(winningNumbers.drawDate()).isEqualTo(expectedDate);
    }

    @Test
    void should_return_winning_numbers_from_database() {
        when(numberReceiverFacade.retrieveNextDrawDate()).thenReturn(LocalDateTime.of(2024, 2, 17, 12, 0));

        LocalDateTime expectedDate = LocalDateTime.of(2024, 2, 17, 12, 0);
        Set<Integer> expectedNumbers = Set.of(1, 2, 3, 4, 5, 6);
        // Given
        var winningNumbers = numberGeneratorFacadeWithStaticNumbers.generateWinningNumbers();
        // When
        var winningNumbersFromDatabase = numberGeneratorFacadeWithStaticNumbers.retrieveWinningNumbers(expectedDate);
        // Then
        assertThat(winningNumbersFromDatabase.numbers()).isEqualTo(expectedNumbers);
    }

    @Test
    void should_return_winning_numbers_with_correct_date_from_database() {
        when(numberReceiverFacade.retrieveNextDrawDate()).thenReturn(LocalDateTime.of(2024, 2, 17, 12, 0));

        LocalDateTime expectedDate = LocalDateTime.of(2024, 2, 17, 12, 0);
        Set<Integer> expectedNumbers = Set.of(1, 2, 3, 4, 5, 6);
        // Given
        var winningNumbers = numberGeneratorFacadeWithStaticNumbers.generateWinningNumbers();
        // When
        var winningNumbersFromDatabase = numberGeneratorFacadeWithStaticNumbers.retrieveWinningNumbers(expectedDate);
        // Then
        assertThat(winningNumbersFromDatabase.drawDate()).isEqualTo(expectedDate);
    }

    @Test
    void should_return_winning_numbers_from_method_generate_winning_numbers() {
        when(numberReceiverFacade.retrieveNextDrawDate()).thenReturn(LocalDateTime.of(2024, 2, 17, 12, 0));

        LocalDateTime expectedDate = LocalDateTime.of(2024, 2, 17, 12, 0);
        Set<Integer> expectedNumbers = Set.of(1, 2, 3, 4, 5, 6);
        // Given
        var winningNumbers = numberGeneratorFacadeWithStaticNumbers.generateWinningNumbers();
        // When
        var winningNumbersFromDatabase = numberGeneratorFacadeWithStaticNumbers.retrieveWinningNumbers(expectedDate);
        // Then
        assertThat(winningNumbers.numbers()).isEqualTo(expectedNumbers);
    }

    @Test
    void should_return_winning_numbers_with_correct_date_from_method_generate_winning_numbers() {
        when(numberReceiverFacade.retrieveNextDrawDate()).thenReturn(LocalDateTime.of(2024, 2, 17, 12, 0));

        LocalDateTime expectedDate = LocalDateTime.of(2024, 2, 17, 12, 0);
        Set<Integer> expectedNumbers = Set.of(1, 2, 3, 4, 5, 6);
        // Given
        var winningNumbers = numberGeneratorFacadeWithStaticNumbers.generateWinningNumbers();
        // When
        var winningNumbersFromDatabase = numberGeneratorFacadeWithStaticNumbers.retrieveWinningNumbers(expectedDate);
        // Then
        assertThat(winningNumbers.drawDate()).isEqualTo(expectedDate);
    }

    @Test
    void should_return_winning_numbers_with_correct_date_from_database_test_2() {
        when(numberReceiverFacade.retrieveNextDrawDate()).thenReturn(LocalDateTime.of(2024, 1, 6, 12, 0));

        LocalDateTime expectedDate = LocalDateTime.of(2024, 1, 6, 12, 0);
        Set<Integer> expectedNumbers = Set.of(1, 2, 3, 4, 5, 6);
        // Given
        var winningNumbers = numberGeneratorFacadeWithStaticNumbers.generateWinningNumbers();
        // When
        var winningNumbersFromDatabase = numberGeneratorFacadeWithStaticNumbers.retrieveWinningNumbers(expectedDate);
        // Then
        assertThat(winningNumbersFromDatabase.drawDate()).isEqualTo(expectedDate);
    }

    @Test
    void should_throw_exception_while_fetching_numbers_from_database_test_2() {
        when(numberReceiverFacade.retrieveNextDrawDate()).thenReturn(LocalDateTime.of(2024, 1, 6, 12, 0));

        LocalDateTime expectedDate = LocalDateTime.of(2024, 1, 6, 12, 0);
        Set<Integer> expectedNumbers = Set.of(1, 2, 3, 4, 5, 6);
        // Then
        assertThrows(NotFoundInDatabaseException.class, () -> numberGeneratorFacadeWithStaticNumbers.retrieveWinningNumbers(expectedDate));
    }

    @Test
    void should_throw_exception_while_range_of_numbers_are_not_correct() {
        when(numberReceiverFacade.retrieveNextDrawDate()).thenReturn(LocalDateTime.of(2024, 1, 6, 12, 0));
        // Then
        assertThrows(IllegalStateException.class, () -> numberGeneratorFacadeWithStaticIncorrectRangeOfNumbers.generateWinningNumbers());
    }

    @Test
    void should_throw_exception_while_size_set_ofnumbers_are_not_correct() {
        when(numberReceiverFacade.retrieveNextDrawDate()).thenReturn(LocalDateTime.of(2024, 1, 6, 12, 0));
        // Then
        assertThrows(IllegalStateException.class, () -> numberGeneratorFacadeWithStaticIncorrectSizeSetOfNumbers.generateWinningNumbers());
    }
}