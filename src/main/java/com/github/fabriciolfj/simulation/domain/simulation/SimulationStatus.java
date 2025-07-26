package com.github.fabriciolfj.simulation.domain.simulation;

import java.util.Arrays;

public enum SimulationStatus {
    PENDING("PENDING"),
    UNDER_REVIEW("UNDER_REVIEW"),
    APPROVED("APPROVED"),
    REJECTED("REJECTED"),
    CANCELLED("CANCELLED"),
    CONTRACTED("CONTRACTED");

    private final String value;

    SimulationStatus(String value) {
        this.value = value;
    }

    public static SimulationStatus toValue(String value) {
        return Arrays.stream(values()).filter(status -> status.value.equals(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Invalid status: " + value));
    }
}