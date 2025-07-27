package com.github.fabriciolfj.simulation.exceptions.clazz;

import static com.github.fabriciolfj.simulation.exceptions.message.BundleMessage.SAVE_SIMULATION;

public class SaveSimulationException extends InfrastructureException {

    public SaveSimulationException() {
        super(SAVE_SIMULATION.toMessage());
    }
}
