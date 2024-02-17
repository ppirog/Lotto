package org.lotto.domain.numberreceiver;

import java.util.UUID;

class IdGenerator implements IdGenerable{

    @Override
    public String generateId() {
        return UUID.randomUUID().toString();
    }
}
