package com.formento.aop.exception.impl;

import com.formento.aop.exception.ApplicationExceptionMessage;

import java.io.Serializable;

public class ApplicationExceptionMessageDefault implements ApplicationExceptionMessage, Serializable {

    private final String message;

    public ApplicationExceptionMessageDefault(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
