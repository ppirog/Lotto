package org.lotto.domain.resultchecker;

import java.util.Set;


class TicketValidator {
    Integer howManyNumbersWin(Set<Integer> userNumbers, Set<Integer> winningNumbers){
        return (int) userNumbers.stream().filter(winningNumbers::contains).count();
    }
    Set<Integer> winningUserNumbers(Set<Integer> userNumbers, Set<Integer> winningNumbers){
        return userNumbers.stream().filter(winningNumbers::contains).collect(java.util.stream.Collectors.toSet());
    }
    boolean isWinner(Set<Integer> userNumbers, Set<Integer> winningNumbers){
        return howManyNumbersWin(userNumbers, winningNumbers) >= 3;
    }
}