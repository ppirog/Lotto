package org.lotto.domain.numberreceiver;

import org.junit.jupiter.api.Test;
import org.lotto.domain.numberreceiver.dto.InputNumberResultDto;
import org.lotto.domain.numberreceiver.dto.TicketDto;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NumberReceiverFacadeTest {


    @Test
    public void should_return_success_when_input_numbers_size_is_six() {

        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(
                new NumberValidator(),
                new InMemoryTicketRepositoryTestImpl(),
                new AdjustableClock(LocalDateTime.of(2024, 2, 14, 0, 0).toInstant(ZoneOffset.UTC), ZoneId.systemDefault()),
                new IdGeneratorTestImpl()
        );

        final InputNumberResultDto inputted = numberReceiverFacade.inputNumbers(Set.of(1, 2, 3, 4, 5, 6));
        assertThat(inputted.message()).isEqualTo("success");
    }

    @Test
    public void should_return_failure_when_input_numbers_size_is_five() {
        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(
                new NumberValidator(),
                new InMemoryTicketRepositoryTestImpl(),
                new AdjustableClock(LocalDateTime.of(2024, 2, 14, 0, 0).toInstant(ZoneOffset.UTC), ZoneId.systemDefault()),
                new IdGeneratorTestImpl()
        );

        final InputNumberResultDto inputted = numberReceiverFacade.inputNumbers(Set.of(1, 2, 3, 4, 5));
        assertThat(inputted.message()).isEqualTo("failure");
    }

    @Test
    public void should_return_failure_when_input_numbers_size_is_seven() {

        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(
                new NumberValidator(),
                new InMemoryTicketRepositoryTestImpl(),
                new AdjustableClock(LocalDateTime.of(2024, 2, 14, 0, 0).toInstant(ZoneOffset.UTC), ZoneId.systemDefault()),
                new IdGeneratorTestImpl()
        );

        final InputNumberResultDto inputted = numberReceiverFacade.inputNumbers(Set.of(1, 2, 3, 4, 5, 6, 7));
        assertThat(inputted.message()).isEqualTo("failure");
    }

    @Test
    public void should_return_failure_when_input_numbers_are_not_in_range_1_99_test_1() {

        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(
                new NumberValidator(),
                new InMemoryTicketRepositoryTestImpl(),
                new AdjustableClock(LocalDateTime.of(2024, 2, 14, 0, 0).toInstant(ZoneOffset.UTC), ZoneId.systemDefault()),
                new IdGeneratorTestImpl()
        );

        final InputNumberResultDto inputted = numberReceiverFacade.inputNumbers(Set.of(111, 2, 3, 4, 5, 6));
        assertThat(inputted.message()).isEqualTo("failure");
    }

    @Test
    public void should_return_failure_when_input_numbers_are_not_in_range_1_99_test_2() {

        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(
                new NumberValidator(),
                new InMemoryTicketRepositoryTestImpl(),
                new AdjustableClock(LocalDateTime.of(2024, 2, 14, 0, 0).toInstant(ZoneOffset.UTC), ZoneId.systemDefault()),
                new IdGeneratorTestImpl()
        );

        final InputNumberResultDto inputted = numberReceiverFacade.inputNumbers(Set.of(0, 2, 3, 4, 5, 99));
        assertThat(inputted.message()).isEqualTo("failure");
    }

    @Test
    public void should_return_failure_when_input_numbers_are_not_in_range_1_99_test_3() {

        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(
                new NumberValidator(),
                new InMemoryTicketRepositoryTestImpl(),
                new AdjustableClock(LocalDateTime.of(2024, 2, 14, 0, 0).toInstant(ZoneOffset.UTC), ZoneId.systemDefault()),
                new IdGeneratorTestImpl()
        );

        final InputNumberResultDto inputted = numberReceiverFacade.inputNumbers(Set.of(1, 2, 3, 4, 5, 100));
        assertThat(inputted.message()).isEqualTo("failure");
    }

    @Test
    public void should_return_success_when_input_numbers_are_in_range_1_99() {

        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(
                new NumberValidator(),
                new InMemoryTicketRepositoryTestImpl(),
                new AdjustableClock(LocalDateTime.of(2024, 2, 14, 0, 0).toInstant(ZoneOffset.UTC), ZoneId.systemDefault()),
                new IdGeneratorTestImpl()
        );

        final InputNumberResultDto inputted = numberReceiverFacade.inputNumbers(Set.of(1, 2, 3, 4, 5, 99));
        assertThat(inputted.message()).isEqualTo("success");
    }

    @Test
    public void should_return_save_to_database_when_user_give_six_numbers_test_1() {

        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(
                new NumberValidator(),
                new InMemoryTicketRepositoryTestImpl(),
                new AdjustableClock(LocalDateTime.of(2024, 2, 14, 0, 0).toInstant(ZoneOffset.UTC), ZoneId.systemDefault()),
                new IdGeneratorTestImpl()
        );

        final Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6);

        final InputNumberResultDto inputted = numberReceiverFacade.inputNumbers(numbersFromUser);

        final LocalDateTime localDateTime = LocalDateTime.of(2024, 2, 14, 0, 0);

        List<TicketDto> userNumbers = numberReceiverFacade.userNumbers(localDateTime);

        assertThat(userNumbers).contains(TicketDto.builder()
                .ticketOrderDate(inputted.ticketDto().ticketOrderDate())
                .drawDate(inputted.ticketDto().drawDate())
                .ticketId(inputted.ticketDto().ticketId())
                .userNumbers(numbersFromUser)
                .build());
    }

    @Test
    public void should_not_return_save_to_database_when_user_give_six_numbers_test_1() {

        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(
                new NumberValidator(),
                new InMemoryTicketRepositoryTestImpl(),
                new AdjustableClock(LocalDateTime.of(2024, 2, 24, 0, 0).toInstant(ZoneOffset.UTC), ZoneId.systemDefault()),
                new IdGeneratorTestImpl()
        );

        final Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6);

        final InputNumberResultDto inputted = numberReceiverFacade.inputNumbers(numbersFromUser);

        final LocalDateTime localDateTime = LocalDateTime.of(2024, 2, 14, 10, 0);

        List<TicketDto> userNumbers = numberReceiverFacade.userNumbers(localDateTime);

        assertThat(userNumbers).doesNotContain(TicketDto.builder()
                .ticketOrderDate(inputted.ticketDto().ticketOrderDate())
                .ticketId(inputted.ticketDto().ticketId())
                .userNumbers(numbersFromUser)
                .build());
    }

    @Test
    public void should_return_correct_draw_date_to_user_when_date_is_exacly_the_same_as_draw_date() {

        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(
                new NumberValidator(),
                new InMemoryTicketRepositoryTestImpl(),
                new AdjustableClock(LocalDateTime.of(2024, 2, 17, 12, 0).toInstant(ZoneOffset.UTC), ZoneId.systemDefault()),
                new IdGeneratorTestImpl()
        );

        final Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6);

        final LocalDateTime drawDate = numberReceiverFacade.inputNumbers(numbersFromUser).ticketDto().drawDate();

        final LocalDateTime expectedDrawDate = LocalDateTime.of(2024, 2, 24, 12, 0);

        assertThat(drawDate).isEqualTo(expectedDrawDate);

    }

    @Test
    public void should_return_correct_draw_date_to_user_when_day_of_week_is_before_saturday() {

        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(
                new NumberValidator(),
                new InMemoryTicketRepositoryTestImpl(),
                new AdjustableClock(LocalDateTime.of(2024, 2, 15, 12, 0).toInstant(ZoneOffset.UTC), ZoneId.systemDefault()),
                new IdGeneratorTestImpl()
        );

        final Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6);

        final LocalDateTime drawDate = numberReceiverFacade.inputNumbers(numbersFromUser).ticketDto().drawDate();

        final LocalDateTime expectedDrawDate = LocalDateTime.of(2024, 2, 17, 12, 0);

        assertThat(drawDate).isEqualTo(expectedDrawDate);

    }

    @Test
    public void should_return_correct_draw_date_to_user_when_day_of_week_is_the_same_as_day_of_draw_date_and_is_before_noon() {

        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(
                new NumberValidator(),
                new InMemoryTicketRepositoryTestImpl(),
                new AdjustableClock(LocalDateTime.of(2024, 2, 17, 10, 59,59).toInstant(ZoneOffset.UTC), ZoneId.systemDefault()),// here is 10 h not 12 because it is greenich time not polish
                new IdGeneratorTestImpl()
        );

        final Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6);

        final LocalDateTime drawDate = numberReceiverFacade
                .inputNumbers(numbersFromUser)
                .ticketDto()
                .drawDate();

        final LocalDateTime expectedDrawDate = LocalDateTime.of(2024, 2, 17, 12, 0);

        assertThat(drawDate).isEqualTo(expectedDrawDate);

    }

    @Test
    public void should_return_correct_draw_date_to_user_when_day_of_week_is_the_same_as_day_of_draw_date_and_is_after_noon() {

        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(
                new NumberValidator(),
                new InMemoryTicketRepositoryTestImpl(),
                new AdjustableClock(LocalDateTime.of(2024, 2, 17, 11, 00,01).toInstant(ZoneOffset.UTC), ZoneId.systemDefault()), // here is 11 h not 12 because it is greenich time not polish
                new IdGeneratorTestImpl()
        );

        final Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6);

        final LocalDateTime drawDate = numberReceiverFacade
                .inputNumbers(numbersFromUser)
                .ticketDto()
                .drawDate();

        final LocalDateTime expectedDrawDate = LocalDateTime.of(2024, 2, 24, 12, 0);

        assertThat(drawDate).isEqualTo(expectedDrawDate);

    }

    @Test
    public void should_return_correct_draw_date_to_user_when_day_of_week_is_after_saturday() {

        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(
                new NumberValidator(),
                new InMemoryTicketRepositoryTestImpl(),
                new AdjustableClock(LocalDateTime.of(2024, 2, 18, 12, 0).toInstant(ZoneOffset.UTC), ZoneId.systemDefault()),
                new IdGeneratorTestImpl()
        );

        final Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6);

        final LocalDateTime drawDate = numberReceiverFacade
                .inputNumbers(numbersFromUser)
                .ticketDto()
                .drawDate();

        final LocalDateTime expectedDrawDate = LocalDateTime.of(2024, 2, 24, 12, 0);

        assertThat(drawDate).isEqualTo(expectedDrawDate);
    }

    @Test
    public void should_return_correct_id_to_user() {

        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(
                new NumberValidator(),
                new InMemoryTicketRepositoryTestImpl(),
                new AdjustableClock(LocalDateTime.of(2024, 2, 14, 0, 0).toInstant(ZoneOffset.UTC), ZoneId.systemDefault()),
                new IdGeneratorTestImpl()
        );

        final Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6);
        final String id = numberReceiverFacade.inputNumbers(numbersFromUser).ticketDto().ticketId();

        final String expectedId = "1234-1234-1234-1234-1234-1234-1234-1234";

        assertThat(id).isEqualTo(expectedId);

    }

    @Test
    public void should_return_correct_size_of_random_id_to_user() {

        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(
                new NumberValidator(),
                new InMemoryTicketRepositoryTestImpl(),
                new AdjustableClock(LocalDateTime.of(2024, 2, 14, 0, 0).toInstant(ZoneOffset.UTC), ZoneId.systemDefault()),
                new IdGenerator()
        );

        final Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6);
        final String id = numberReceiverFacade.inputNumbers(numbersFromUser).ticketDto().ticketId();

        assertThat(id).hasSize(36);

    }

    @Test
    public void should_return_correct_numbers_from_database_by_id(){

        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(
                new NumberValidator(),
                new InMemoryTicketRepositoryTestImpl(),
                new AdjustableClock(LocalDateTime.of(2024, 2, 14, 0, 0).toInstant(ZoneOffset.UTC), ZoneId.systemDefault()),
                new IdGeneratorTestImpl()
        );

        final InputNumberResultDto inputNumberResultDto = numberReceiverFacade.inputNumbers(Set.of(1, 2, 3, 4, 5, 6));
        final String generatedTicketId = inputNumberResultDto.ticketDto().ticketId();

        final TicketDto byId = numberReceiverFacade.usersNumberByTicketId(generatedTicketId);

        assertThat(byId.userNumbers()).isEqualTo(inputNumberResultDto.ticketDto().userNumbers());

    }

    @Test
    public void should_throw_exception_when_not_found_numbers_by_ticket_id_from_database(){

        NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(
                new NumberValidator(),
                new InMemoryTicketRepositoryTestImpl(),
                new AdjustableClock(LocalDateTime.of(2024, 2, 14, 0, 0).toInstant(ZoneOffset.UTC), ZoneId.systemDefault()),
                new IdGeneratorTestImpl()
        );

        assertThrows(NotFoundInDatabaseException.class, () -> numberReceiverFacade.usersNumberByTicketId("notFoundId"));
    }


}