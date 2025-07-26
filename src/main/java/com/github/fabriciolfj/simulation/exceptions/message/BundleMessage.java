package com.github.fabriciolfj.simulation.exceptions.message;

import java.util.ResourceBundle;

public enum BundleMessage {

    BUSINESS_ERROR,
    INFRA_ERROR,
    DEFAULT_ERROR;


    public String toMessage() {
        var bundle = ResourceBundle.getBundle("exception/messages");
        return bundle.getString(this.name() + ".message");
    }
}
