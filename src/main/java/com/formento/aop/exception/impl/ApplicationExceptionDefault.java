package com.formento.aop.exception.impl;

import com.formento.aop.exception.ApplicationException;
import com.formento.aop.exception.ApplicationExceptionMessage;

public class ApplicationExceptionDefault extends RuntimeException implements ApplicationException {

    public ApplicationExceptionDefault(String s) {
        super(s);
    }

    @Override
    public ApplicationExceptionMessage buildApplicationExceptionMessage() {
        return new ApplicationExceptionMessageDefault(getMessage());
    }

}
