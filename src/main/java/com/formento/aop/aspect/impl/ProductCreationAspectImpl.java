package com.formento.aop.aspect.impl;

import com.formento.aop.aspect.ProductCreationAspect;
import com.formento.aop.model.Product;
import com.formento.aop.validation.ProductCreationValidator;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.concurrent.Immutable;

@Component
@Aspect
@Immutable
public class ProductCreationAspectImpl implements ProductCreationAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductCreationAspectImpl.class);

    private final ProductCreationValidator productCreationValidator;

    @Autowired
    public ProductCreationAspectImpl(final ProductCreationValidator productCreationValidator) {
        this.productCreationValidator = productCreationValidator;
    }

    @AfterReturning("execution(* com.formento.aop..*Service.*(..))")
    public void logServiceCreate(JoinPoint joinPoint) {
        LOGGER.info("Created: {}", joinPoint);
    }


    @Before("execution(* com.formento.aop.service.impl.ProductServiceImpl.create(..)) && args(product,..)")
    public void validate(Product product) {
        LOGGER.info("validate");
        productCreationValidator.validate(product);
    }

}
