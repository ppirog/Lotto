package org.lotto.domain.numberannouncer;

import org.lotto.domain.numberannouncer.NumberAnnouncerFacade;
import org.lotto.domain.numberannouncer.ResultRepository;
import org.lotto.domain.resultchecker.ResultCheckerFacade;

import java.time.LocalDateTime;

class TestNumberAnnouncerFacade extends NumberAnnouncerFacade {
    private final LocalDateTime fixedDateTime;
    public TestNumberAnnouncerFacade(final ResultCheckerFacade resultCheckerFacade, final ResultRepository resultRepository, LocalDateTime fixedDateTime) {
        super(resultCheckerFacade, resultRepository);
        this.fixedDateTime = fixedDateTime;
    }

    @Override
    public LocalDateTime getNow() {
        return fixedDateTime;
    }
}
