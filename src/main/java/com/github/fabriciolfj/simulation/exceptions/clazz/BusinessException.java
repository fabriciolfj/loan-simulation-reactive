package com.github.fabriciolfj.simulation.exceptions.clazz;

import static com.github.fabriciolfj.simulation.exceptions.message.BundleMessage.BUSINESS_ERROR;

public class BusinessException extends RuntimeException {

    public BusinessException() {
        super(BUSINESS_ERROR.toMessage());
    }

    public BusinessException(final String msg) {
        super(msg);
    }
}
