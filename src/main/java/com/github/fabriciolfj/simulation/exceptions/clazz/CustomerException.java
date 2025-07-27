package com.github.fabriciolfj.simulation.exceptions.clazz;

import static com.github.fabriciolfj.simulation.exceptions.message.BundleMessage.CUSTOMER_ERROR;

public class CustomerException extends BusinessException {

    public CustomerException() {
        super(CUSTOMER_ERROR.toMessage());
    }
}
