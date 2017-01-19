package com.formento.aop.exception.impl;

import com.formento.aop.exception.ApplicationException;
import com.formento.aop.exception.ApplicationExceptionMessage;
import com.formento.aop.exception.ApplicationHandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApplicationHandlerImpl implements ApplicationHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ApplicationExceptionDefault.class)
    @ResponseBody
    public ApplicationExceptionMessage handleBadRequest(ApplicationException e) {
        return e.buildApplicationExceptionMessage();
    }

}
