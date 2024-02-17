package org.lotto.domain.numbergenerator;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

class InMemoryWinningNumbersRepositoryTestImpl implements WinningNumbersRepository {

    private final Map<LocalDateTime, WinningNumbers> winningNumbersMap = new ConcurrentHashMap<>();

    @Override
    public WinningNumbers save(final WinningNumbers winningNumbers) {
        return winningNumbersMap.put(winningNumbers.date(), winningNumbers);
    }

    @Override
    public Optional<WinningNumbers> findByDate(final LocalDateTime date) {
        return Optional.ofNullable(winningNumbersMap.get(date));
    }

    @Override
    public boolean existsByDate(final LocalDateTime date) {
        return winningNumbersMap.containsKey(date);
    }
}
