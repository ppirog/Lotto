package org.lotto.domain.numberreceiver;

class IdGeneratorTestImpl implements IdGenerable{

    private String id;

    IdGeneratorTestImpl(final String id) {
        this.id = id;
    }

    IdGeneratorTestImpl() {
        this.id = "1234-1234-1234-1234-1234-1234-1234-1234";
    }

    @Override
    public String generateId() {
        return id;
    }
}
