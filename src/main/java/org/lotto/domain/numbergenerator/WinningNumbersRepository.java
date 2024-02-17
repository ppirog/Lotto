package org.lotto.domain.numbergenerator;

import java.time.LocalDateTime;
import java.util.Optional;

interface WinningNumbersRepository {

    WinningNumbers save(WinningNumbers winningNumbers);

    Optional<WinningNumbers> findByDate(LocalDateTime date);

    boolean existsByDate(LocalDateTime date);
}
