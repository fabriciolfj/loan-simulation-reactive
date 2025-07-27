package com.github.fabriciolfj.simulation.exceptions.message;

import java.util.ResourceBundle;

public enum BundleMessage {

    BUSINESS_ERROR,
    INFRA_ERROR,
    CALCULATION_NOT_FOUND,
    PARAMETER_UNAVAILABLE,
    FAIL_GET_PARAMETER,
    DEFAULT_ERROR;


    public String toMessage() {
        var bundle = ResourceBundle.getBundle("exception/messages");
        return bundle.getString(this.name() + ".message");
    }
}
