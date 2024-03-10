package org.lotto.domain.resultchecker;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.lotto.domain.numbergenerator.NumberGeneratorFacade;
import org.lotto.domain.numbergenerator.dto.WinningNumbersDto;
import org.lotto.domain.numberreceiver.NotFoundInDatabaseException;
import org.lotto.domain.numberreceiver.NumberReceiverFacade;
import org.lotto.domain.numberreceiver.dto.TicketDto;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ResultCheckerFacadeTest {

    NumberReceiverFacade numberReceiverFacade = mock(NumberReceiverFacade.class);
    NumberGeneratorFacade numberGeneratorFacade = mock(NumberGeneratorFacade.class);



    @Test
    void should_return_number_of_won_numbers_6_numbers_matched() {
        when(numberReceiverFacade.getNow()).thenReturn(LocalDateTime.of(2024, 2, 17, 12, 5));


        when(numberReceiverFacade.usersNumberByTicketId("ticketId")).thenReturn(
                new TicketDto(LocalDateTime.of(2024, 2, 15, 12, 0),
                        LocalDateTime.of(2024, 2, 17, 12, 0),
                        "ticketId",
                        Set.of(1, 2, 3, 4, 5, 6)
                )
        );
        when(numberGeneratorFacade.retrieveWinningNumbers(LocalDateTime.of(2024, 2, 17, 12, 0))).thenReturn(
                new WinningNumbersDto(
                        Set.of(1, 2, 3, 4, 5, 6),
                        LocalDateTime.of(2024, 2, 17, 12, 0))
        );
        // Given
        var resultCheckerFacade = new ResultCheckerFacade(numberReceiverFacade, numberGeneratorFacade);
        // When
        var playerDto = resultCheckerFacade.findById("ticketId");
        // Then
        assertThat(playerDto.howManyNumbersWin()).isEqualTo(6);
    }

    @Test
    void should_return_number_of_won_numbers_2_numbers_matched() {
        when(numberReceiverFacade.getNow()).thenReturn(LocalDateTime.of(2024, 2, 17, 12, 5));
        when(numberReceiverFacade.usersNumberByTicketId("ticketId")).thenReturn(
                new TicketDto(LocalDateTime.of(2024, 2, 15, 12, 0),
                        LocalDateTime.of(2024, 2, 17, 12, 0),
                        "ticketId",
                        Set.of(1, 2, 32, 43, 54, 65)
                )
        );
        when(numberGeneratorFacade.retrieveWinningNumbers(LocalDateTime.of(2024, 2, 17, 12, 0))).thenReturn(
                new WinningNumbersDto(
                        Set.of(1, 2, 3, 4, 5, 6),
                        LocalDateTime.of(2024, 2, 17, 12, 0))
        );
        // Given
        var resultCheckerFacade = new ResultCheckerFacade(numberReceiverFacade, numberGeneratorFacade);
        // When
        var playerDto = resultCheckerFacade.findById("ticketId");
        // Then
        assertThat(playerDto.howManyNumbersWin()).isEqualTo(2);
    }

    @Test
    void should_return_number_of_won_numbers_0_numbers_matched() {
        when(numberReceiverFacade.getNow()).thenReturn(LocalDateTime.of(2024, 2, 17, 12, 5));
        when(numberReceiverFacade.usersNumberByTicketId("ticketId")).thenReturn(
                new TicketDto(LocalDateTime.of(2024, 2, 15, 12, 0),
                        LocalDateTime.of(2024, 2, 17, 12, 0),
                        "ticketId",
                        Set.of(19, 21, 32, 43, 54, 65)
                )
        );
        when(numberGeneratorFacade.retrieveWinningNumbers(LocalDateTime.of(2024, 2, 17, 12, 0))).thenReturn(
                new WinningNumbersDto(
                        Set.of(1, 2, 3, 4, 5, 6),
                        LocalDateTime.of(2024, 2, 17, 12, 0))
        );
        // Given
        var resultCheckerFacade = new ResultCheckerFacade(numberReceiverFacade, numberGeneratorFacade);
        // When
        var playerDto = resultCheckerFacade.findById("ticketId");
        // Then
        assertThat(playerDto.howManyNumbersWin()).isEqualTo(0);
    }
    @Test
    void should_return_information_if_player_won_6_numbers() {
        when(numberReceiverFacade.getNow()).thenReturn(LocalDateTime.of(2024, 2, 17, 12, 5));
        when(numberReceiverFacade.usersNumberByTicketId("ticketId")).thenReturn(
                new TicketDto(LocalDateTime.of(2024, 2, 15, 12, 0),
                        LocalDateTime.of(2024, 2, 17, 12, 0),
                        "ticketId",
                        Set.of(1, 2, 3, 4, 5, 6)
                )
        );
        when(numberGeneratorFacade.retrieveWinningNumbers(LocalDateTime.of(2024, 2, 17, 12, 0))).thenReturn(
                new WinningNumbersDto(
                        Set.of(1, 2, 3, 4, 5, 6),
                        LocalDateTime.of(2024, 2, 17, 12, 0))
        );
        // Given
        var resultCheckerFacade = new ResultCheckerFacade(numberReceiverFacade, numberGeneratorFacade);
        // When
        var playerDto = resultCheckerFacade.findById("ticketId");
        // Then
        assertTrue(playerDto.isWinner());
    }
    @Test
    void should_return_information_if_player_won_3_numbers() {
        when(numberReceiverFacade.getNow()).thenReturn(LocalDateTime.of(2024, 2, 17, 12, 5));
        when(numberReceiverFacade.usersNumberByTicketId("ticketId")).thenReturn(
                new TicketDto(LocalDateTime.of(2024, 2, 15, 12, 0),
                        LocalDateTime.of(2024, 2, 17, 12, 0),
                        "ticketId",
                        Set.of(1, 2, 3, 43, 54, 65)
                )
        );
        when(numberGeneratorFacade.retrieveWinningNumbers(LocalDateTime.of(2024, 2, 17, 12, 0))).thenReturn(
                new WinningNumbersDto(
                        Set.of(1, 2, 3, 4, 5, 6),
                        LocalDateTime.of(2024, 2, 17, 12, 0))
        );
        // Given
        var resultCheckerFacade = new ResultCheckerFacade(numberReceiverFacade, numberGeneratorFacade);
        // When
        var playerDto = resultCheckerFacade.findById("ticketId");
        // Then
        assertTrue(playerDto.isWinner());
    }
    @Test
    void should_return_information_if_player_won_2_numbers() {
        when(numberReceiverFacade.getNow()).thenReturn(LocalDateTime.of(2024, 2, 17, 12, 5));
        when(numberReceiverFacade.usersNumberByTicketId("ticketId")).thenReturn(
                new TicketDto(LocalDateTime.of(2024, 2, 15, 12, 0),
                        LocalDateTime.of(2024, 2, 17, 12, 0),
                        "ticketId",
                        Set.of(1, 2, 32, 43, 54, 65)
                )
        );
        when(numberGeneratorFacade.retrieveWinningNumbers(LocalDateTime.of(2024, 2, 17, 12, 0))).thenReturn(
                new WinningNumbersDto(
                        Set.of(1, 2, 3, 4, 5, 6),
                        LocalDateTime.of(2024, 2, 17, 12, 0))
        );
        // Given
        var resultCheckerFacade = new ResultCheckerFacade(numberReceiverFacade, numberGeneratorFacade);
        // When
        var playerDto = resultCheckerFacade.findById("ticketId");
        // Then
        assertFalse(playerDto.isWinner());
    }
    @Test
    void should_return_information_if_player_won_0_numbers() {
        when(numberReceiverFacade.getNow()).thenReturn(LocalDateTime.of(2024, 2, 17, 12, 5));
        when(numberReceiverFacade.usersNumberByTicketId("ticketId")).thenReturn(
                new TicketDto(LocalDateTime.of(2024, 2, 15, 12, 0),
                        LocalDateTime.of(2024, 2, 17, 12, 0),
                        "ticketId",
                        Set.of(12, 23, 34, 43, 54, 65)
                )
        );
        when(numberGeneratorFacade.retrieveWinningNumbers(LocalDateTime.of(2024, 2, 17, 12, 0))).thenReturn(
                new WinningNumbersDto(
                        Set.of(1, 2, 3, 4, 5, 6),
                        LocalDateTime.of(2024, 2, 17, 12, 0))
        );
        // Given
        var resultCheckerFacade = new ResultCheckerFacade(numberReceiverFacade, numberGeneratorFacade);
        // When
        var playerDto = resultCheckerFacade.findById("ticketId");
        // Then
        assertFalse(playerDto.isWinner());
    }

    @Test
    void should_return_correct_ticket_id_to_player_test_1() {
        when(numberReceiverFacade.getNow()).thenReturn(LocalDateTime.of(2024, 2, 17, 12, 5));
        when(numberReceiverFacade.usersNumberByTicketId("ticketId")).thenReturn(
                new TicketDto(LocalDateTime.of(2024, 2, 15, 12, 0),
                        LocalDateTime.of(2024, 2, 17, 12, 0),
                        "ticketId",
                        Set.of(19, 21, 32, 43, 54, 65)
                )
        );
        when(numberGeneratorFacade.retrieveWinningNumbers(LocalDateTime.of(2024, 2, 17, 12, 0))).thenReturn(
                new WinningNumbersDto(
                        Set.of(1, 2, 3, 4, 5, 6),
                        LocalDateTime.of(2024, 2, 17, 12, 0))
        );
        // Given
        var resultCheckerFacade = new ResultCheckerFacade(numberReceiverFacade, numberGeneratorFacade);
        // When
        var playerDto = resultCheckerFacade.findById("ticketId");
        // Then
        assertThat(playerDto.ticketId()). isEqualTo("ticketId");
    }

    @Test
    void should_return_correct_ticket_id_to_player_test_2() {
        when(numberReceiverFacade.getNow()).thenReturn(LocalDateTime.of(2024, 2, 17, 12, 5));
        when(numberReceiverFacade.usersNumberByTicketId("ticketId2")).thenReturn(
                new TicketDto(LocalDateTime.of(2024, 2, 15, 12, 0),
                        LocalDateTime.of(2024, 2, 17, 12, 0),
                        "ticketId2",
                        Set.of(19, 21, 32, 43, 54, 65)
                )
        );
        when(numberGeneratorFacade.retrieveWinningNumbers(LocalDateTime.of(2024, 2, 17, 12, 0))).thenReturn(
                new WinningNumbersDto(
                        Set.of(1, 2, 3, 4, 5, 6),
                        LocalDateTime.of(2024, 2, 17, 12, 0))
        );
        // Given
        var resultCheckerFacade = new ResultCheckerFacade(numberReceiverFacade, numberGeneratorFacade);
        // When
        var playerDto = resultCheckerFacade.findById("ticketId2");
        // Then
        assertThat(playerDto.ticketId()). isEqualTo("ticketId2");
    }

    @Test
    void should_return_correct_set_of_winning_numbers_to_player() {
        when(numberReceiverFacade.getNow()).thenReturn(LocalDateTime.of(2024, 2, 17, 12, 5));
        when(numberReceiverFacade.usersNumberByTicketId("ticketId")).thenReturn(
                new TicketDto(LocalDateTime.of(2024, 2, 15, 12, 0),
                        LocalDateTime.of(2024, 2, 17, 12, 0),
                        "ticketId",
                        Set.of(19, 21, 32, 43, 54, 65)
                )
        );
        when(numberGeneratorFacade.retrieveWinningNumbers(LocalDateTime.of(2024, 2, 17, 12, 0))).thenReturn(
                new WinningNumbersDto(
                        Set.of(1, 2, 3, 4, 5, 6),
                        LocalDateTime.of(2024, 2, 17, 12, 0))
        );
        // Given
        var resultCheckerFacade = new ResultCheckerFacade(numberReceiverFacade, numberGeneratorFacade);
        // When
        var playerDto = resultCheckerFacade.findById("ticketId");
        // Then
        assertThat(playerDto.winNumbers()). isEqualTo(Set.of());
    }

    @Test
    void should_return_correct_set_of_winning_numbers_to_player_test_2() {
        when(numberReceiverFacade.getNow()).thenReturn(LocalDateTime.of(2024, 2, 17, 12, 5));
        when(numberReceiverFacade.usersNumberByTicketId("ticketId")).thenReturn(
                new TicketDto(LocalDateTime.of(2024, 2, 15, 12, 0),
                        LocalDateTime.of(2024, 2, 17, 12, 0),
                        "ticketId",
                        Set.of(1, 2, 32, 43, 54, 65)
                )
        );
        when(numberGeneratorFacade.retrieveWinningNumbers(LocalDateTime.of(2024, 2, 17, 12, 0))).thenReturn(
                new WinningNumbersDto(
                        Set.of(1, 2, 3, 4, 5, 6),
                        LocalDateTime.of(2024, 2, 17, 12, 0))
        );
        // Given
        var resultCheckerFacade = new ResultCheckerFacade(numberReceiverFacade, numberGeneratorFacade);
        // When
        var playerDto = resultCheckerFacade.findById("ticketId");
        // Then
        assertThat(playerDto.winNumbers()). isEqualTo(Set.of(1,2));
    }

    @Test
    void should_return_correct_set_of_user_numbers_to_player() {
        when(numberReceiverFacade.getNow()).thenReturn(LocalDateTime.of(2024, 2, 17, 12, 5));
        when(numberReceiverFacade.usersNumberByTicketId("ticketId")).thenReturn(
                new TicketDto(LocalDateTime.of(2024, 2, 15, 12, 0),
                        LocalDateTime.of(2024, 2, 17, 12, 0),
                        "ticketId",
                        Set.of(1, 2, 32, 43, 54, 65)
                )
        );
        when(numberGeneratorFacade.retrieveWinningNumbers(LocalDateTime.of(2024, 2, 17, 12, 0))).thenReturn(
                new WinningNumbersDto(
                        Set.of(1, 2, 3, 4, 5, 6),
                        LocalDateTime.of(2024, 2, 17, 12, 0))
        );
        // Given
        var resultCheckerFacade = new ResultCheckerFacade(numberReceiverFacade, numberGeneratorFacade);
        // When
        var playerDto = resultCheckerFacade.findById("ticketId");
        // Then
        assertThat(playerDto.numbers()). isEqualTo(Set.of(1, 2, 32, 43, 54, 65));
    }

    @Test
    void should_throw_exception_when_draw_date_plus_5_min_is_after_now() {
        when(numberReceiverFacade.getNow()).thenReturn(LocalDateTime.of(2024, 2, 17, 12, 4));
        when(numberReceiverFacade.usersNumberByTicketId("ticketId")).thenReturn(
                new TicketDto(LocalDateTime.of(2024, 2, 17, 12, 6),
                        LocalDateTime.of(2024, 2, 17, 12, 0),
                        "ticketId",
                        Set.of(1, 2, 32, 43, 54, 65)
                )
        );
        // Given
        var resultCheckerFacade = new ResultCheckerFacade(numberReceiverFacade, numberGeneratorFacade);
        // When
        // Then
        assertThrows(DrawDateIsAfterNowException.class, () -> resultCheckerFacade.findById("ticketId"));
    }
}