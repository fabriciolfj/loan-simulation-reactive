package com.github.fabriciolfj.simulation.exceptions.clazz;

import static com.github.fabriciolfj.simulation.exceptions.message.BundleMessage.INFRA_ERROR;

public class InfrastructureException extends RuntimeException {

    public InfrastructureException() {
        super(INFRA_ERROR.toMessage());
    }
}
