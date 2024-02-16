package org.lotto.domain.numberreceiver;

import org.junit.jupiter.api.Test;
import org.lotto.domain.numberreceiver.dto.InputNumberResultDto;
import org.lotto.domain.numberreceiver.dto.TicketDto;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class NumberReceiverFacadeTest {
    private NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(
            new NumberValidator(),
            new InMemoryNumberReceiverRepositoryTestImpl()
    );

    @Test
    public void should_return_success_when_input_numbers_size_is_six() {
        final InputNumberResultDto inputted = numberReceiverFacade.inputNumbers(Set.of(1, 2, 3, 4, 5, 6));
        assertThat(inputted.message()).isEqualTo("success");
    }

    @Test
    public void should_return_failure_when_input_numbers_size_is_five() {
        final InputNumberResultDto inputted = numberReceiverFacade.inputNumbers(Set.of(1, 2, 3, 4, 5));
        assertThat(inputted.message()).isEqualTo("failure");
    }

    @Test
    public void should_return_failure_when_input_numbers_size_is_seven() {
        final InputNumberResultDto inputted = numberReceiverFacade.inputNumbers(Set.of(1, 2, 3, 4, 5, 6, 7));
        assertThat(inputted.message()).isEqualTo("failure");
    }

    @Test
    public void should_return_failure_when_input_numbers_are_not_in_range_1_99_test_1() {
        final InputNumberResultDto inputted = numberReceiverFacade.inputNumbers(Set.of(111, 2, 3, 4, 5, 6));
        assertThat(inputted.message()).isEqualTo("failure");
    }

    @Test
    public void should_return_failure_when_input_numbers_are_not_in_range_1_99_test_2() {
        final InputNumberResultDto inputted = numberReceiverFacade.inputNumbers(Set.of(0, 2, 3, 4, 5, 99));
        assertThat(inputted.message()).isEqualTo("failure");
    }

    @Test
    public void should_return_failure_when_input_numbers_are_not_in_range_1_99_test_3() {
        final InputNumberResultDto inputted = numberReceiverFacade.inputNumbers(Set.of(1, 2, 3, 4, 5, 100));
        assertThat(inputted.message()).isEqualTo("failure");
    }

    @Test
    public void should_return_success_when_input_numbers_are_in_range_1_99() {
        final InputNumberResultDto inputted = numberReceiverFacade.inputNumbers(Set.of(1, 2, 3, 4, 5, 99));
        assertThat(inputted.message()).isEqualTo("success");
    }

    @Test
    public void should_return_save_to_database_when_user_give_six_numbers_test_1(){

        final Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6);

        final InputNumberResultDto inputted = numberReceiverFacade.inputNumbers(numbersFromUser);
        List<TicketDto> userNumbers = numberReceiverFacade.userNumbers(inputted.date());

        assertThat(userNumbers).contains(TicketDto.builder()
                .date(inputted.date())
                .ticketId(inputted.ticketId())
                .userNumbers(numbersFromUser)
                .build());
    }

    @Test
    public void should_return_save_to_database_when_user_give_six_numbers_test_2(){

        final Set<Integer> numbersFromUser = Set.of(11, 2, 3, 4, 5, 6);

        final InputNumberResultDto inputted = numberReceiverFacade.inputNumbers(numbersFromUser);
        List<TicketDto> userNumbers = numberReceiverFacade.userNumbers(inputted.date());

        assertThat(userNumbers).contains(TicketDto.builder()
                .date(inputted.date())
                .ticketId(inputted.ticketId())
                .userNumbers(numbersFromUser)
                .build());
    }

    @Test
    public void should_return_save_to_database_when_user_give_six_numbers_test_3(){

        final Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 99);

        final InputNumberResultDto inputted = numberReceiverFacade.inputNumbers(numbersFromUser);
        List<TicketDto> userNumbers = numberReceiverFacade.userNumbers(inputted.date());

        assertThat(userNumbers).contains(TicketDto.builder()
                .date(inputted.date())
                .ticketId(inputted.ticketId())
                .userNumbers(numbersFromUser)
                .build());
    }


}