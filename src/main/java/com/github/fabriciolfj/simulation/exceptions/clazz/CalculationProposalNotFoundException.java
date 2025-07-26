package com.github.fabriciolfj.simulation.exceptions.clazz;

import static com.github.fabriciolfj.simulation.exceptions.message.BundleMessage.CALCULATION_NOT_FOUND;

public class CalculationProposalNotFoundException extends RuntimeException {

    public CalculationProposalNotFoundException() {
        super(CALCULATION_NOT_FOUND.toMessage());
    }
}
