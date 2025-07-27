package com.github.fabriciolfj.simulation.exceptions.clazz;

import static com.github.fabriciolfj.simulation.exceptions.message.BundleMessage.FAIL_GET_PARAMETER;

public class FailRequestParameterException extends BusinessException {

    public FailRequestParameterException() {
        super(FAIL_GET_PARAMETER.toMessage());
    }
}
