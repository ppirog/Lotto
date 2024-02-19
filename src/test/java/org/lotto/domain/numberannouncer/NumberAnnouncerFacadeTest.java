package org.lotto.domain.numberannouncer;


import org.junit.jupiter.api.Test;
import org.lotto.domain.numberannouncer.dto.ResultDto;
import org.lotto.domain.resultchecker.ResultCheckerFacade;
import org.lotto.domain.resultchecker.dto.PlayerDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NumberAnnouncerFacadeTest {


    private final ResultCheckerFacade resultCheckerFacade = mock(ResultCheckerFacade.class);

    @Test
    void should_announce_correct_winner_numbers() {

        when(resultCheckerFacade.findById("1")).thenReturn(
                PlayerDto.builder()
                        .ticketId("1")
                        .isWinner(true)
                        .howManyNumbersWin(6)
                        .winNumbers(Set.of(1,2,3,4,5,6))
                        .drawDate(LocalDateTime.of(2024, 2, 17, 12, 0))
                        .build()
        );

        //given
        NumberAnnouncerFacade numberAnnouncerFacade = new NumberAnnouncerFacade(
                resultCheckerFacade,
                new InMemoryResultRepositoryTestImpl()
        );

        //when
        ResultDto resultDto = numberAnnouncerFacade.announceResult("1");

        //then
        assertEquals(resultDto.winNumbers(), Set.of(1,2,3,4,5,6));
    }

    @Test
    void should_announce_correct_ticket_id() {

        when(resultCheckerFacade.findById("1")).thenReturn(
                PlayerDto.builder()
                        .ticketId("1")
                        .isWinner(true)
                        .howManyNumbersWin(6)
                        .winNumbers(Set.of(1,2,3,4,5,6))
                        .drawDate(LocalDateTime.of(2024, 2, 17, 12, 0))
                        .build()
        );

        //given
        NumberAnnouncerFacade numberAnnouncerFacade = new NumberAnnouncerFacade(
                resultCheckerFacade,
                new InMemoryResultRepositoryTestImpl()
        );

        //when
        ResultDto resultDto = numberAnnouncerFacade.announceResult("1");

        //then
        assertEquals(resultDto.ticketId(), "1");
    }

    @Test
    void should_announce_correct_numbers_of_win() {

        when(resultCheckerFacade.findById("1")).thenReturn(
                PlayerDto.builder()
                        .ticketId("1")
                        .isWinner(true)
                        .howManyNumbersWin(6)
                        .winNumbers(Set.of(1,2,3,4,5,6))
                        .drawDate(LocalDateTime.of(2024, 2, 17, 12, 0))
                        .build()
        );

        //given
        NumberAnnouncerFacade numberAnnouncerFacade = new NumberAnnouncerFacade(
                resultCheckerFacade,
                new InMemoryResultRepositoryTestImpl()
        );

        //when
        ResultDto resultDto = numberAnnouncerFacade.announceResult("1");

        //then
        assertEquals(resultDto.howManyNumbersWin(), 6);
    }

    @Test
    void should_announce_if_player_is_winner() {

        when(resultCheckerFacade.findById("1")).thenReturn(
                PlayerDto.builder()
                        .ticketId("1")
                        .isWinner(true)
                        .howManyNumbersWin(6)
                        .winNumbers(Set.of(1,2,3,4,5,6))
                        .drawDate(LocalDateTime.of(2024, 2, 17, 12, 0))
                        .build()
        );

        //given
        NumberAnnouncerFacade numberAnnouncerFacade = new NumberAnnouncerFacade(
                resultCheckerFacade,
                new InMemoryResultRepositoryTestImpl()
        );

        //when
        ResultDto resultDto = numberAnnouncerFacade.announceResult("1");

        //then
        assertTrue(resultDto.isWinner());
    }

    @Test
    void should_check_if_database_store_only_results_which_are_not_older_than_one_month_test_1() {

        when(resultCheckerFacade.findById("1")).thenReturn(
                PlayerDto.builder()
                        .ticketId("1")
                        .isWinner(true)
                        .howManyNumbersWin(6)
                        .winNumbers(Set.of(1,2,3,4,5,6))
                        .drawDate(LocalDateTime.of(2024, 2, 17, 12, 0))
                        .build()
        );

        when(resultCheckerFacade.findById("2")).thenReturn(
                PlayerDto.builder()
                        .ticketId("2")
                        .isWinner(true)
                        .howManyNumbersWin(5)
                        .winNumbers(Set.of(1,2,3,4,5))
                        .drawDate(LocalDateTime.of(2023, 2, 17, 12, 0))
                        .build()
        );

        when(resultCheckerFacade.findById("3")).thenReturn(
                PlayerDto.builder()
                        .ticketId("3")
                        .isWinner(true)
                        .howManyNumbersWin(4)
                        .winNumbers(Set.of(1,2,3,4))
                        .drawDate(LocalDateTime.of(2022, 2, 17, 12, 0))
                        .build()
        );

        //given
        final InMemoryResultRepositoryTestImpl resultRepository = new InMemoryResultRepositoryTestImpl();
        NumberAnnouncerFacade numberAnnouncerFacade = new NumberAnnouncerFacade(
                resultCheckerFacade,
                resultRepository
        );

        //when
        numberAnnouncerFacade.announceResult("1");
        numberAnnouncerFacade.announceResult("2");
        numberAnnouncerFacade.announceResult("3");

        final List<Result> resultsOlderThanOneMonth = resultRepository.findResultsOlderThanOneMonth();

        final List<Result> all = resultRepository.findAll();

        //then
        assertEquals(0, resultsOlderThanOneMonth.size());
        assertEquals(1, all.size());
    }

    @Test
    void should_check_if_database_store_only_results_which_are_not_older_than_one_month_test_2() {

        when(resultCheckerFacade.findById("1")).thenReturn(
                PlayerDto.builder()
                        .ticketId("1")
                        .isWinner(true)
                        .howManyNumbersWin(6)
                        .winNumbers(Set.of(1,2,3,4,5,6))
                        .drawDate(LocalDateTime.of(2024, 2, 17, 12, 0))
                        .build()
        );

        when(resultCheckerFacade.findById("2")).thenReturn(
                PlayerDto.builder()
                        .ticketId("2")
                        .isWinner(true)
                        .howManyNumbersWin(5)
                        .winNumbers(Set.of(1,2,3,4,5))
                        .drawDate(LocalDateTime.of(2024, 2, 3, 12, 0))
                        .build()
        );

        when(resultCheckerFacade.findById("3")).thenReturn(
                PlayerDto.builder()
                        .ticketId("3")
                        .isWinner(true)
                        .howManyNumbersWin(4)
                        .winNumbers(Set.of(1,2,3,4))
                        .drawDate(LocalDateTime.of(2022, 2, 17, 12, 0))
                        .build()
        );

        //given
        final InMemoryResultRepositoryTestImpl resultRepository = new InMemoryResultRepositoryTestImpl();
        NumberAnnouncerFacade numberAnnouncerFacade = new NumberAnnouncerFacade(
                resultCheckerFacade,
                resultRepository
        );

        //when
        numberAnnouncerFacade.announceResult("1");
        numberAnnouncerFacade.announceResult("2");
        numberAnnouncerFacade.announceResult("3");

        final List<Result> resultsOlderThanOneMonth = resultRepository.findResultsOlderThanOneMonth();

        final List<Result> all = resultRepository.findAll();

        //then
        assertEquals(0, resultsOlderThanOneMonth.size());
        assertEquals(2, all.size());
    }
}