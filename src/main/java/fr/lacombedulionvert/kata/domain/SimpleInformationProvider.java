package fr.lacombedulionvert.kata.domain;

import java.math.BigDecimal;

public class SimpleInformationProvider implements InformationProvider {

    @Override
    public BigDecimal giveOverdraftLimit() {
        return BigDecimal.valueOf(0);
    }
}
