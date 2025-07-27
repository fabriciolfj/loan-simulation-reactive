package com.github.fabriciolfj.simulation.exceptions.clazz;

import static com.github.fabriciolfj.simulation.exceptions.message.BundleMessage.PARAMETER_UNAVAILABLE;

public class ParameterUnavailableException extends InfrastructureException {

    public ParameterUnavailableException() {
        super(PARAMETER_UNAVAILABLE.toMessage());
    }
}
