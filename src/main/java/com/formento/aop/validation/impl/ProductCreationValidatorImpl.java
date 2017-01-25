package com.formento.aop.validation.impl;

import com.formento.aop.exception.impl.ApplicationExceptionDefault;
import com.formento.aop.model.Product;
import com.formento.aop.validation.ProductCreationValidator;
import org.springframework.stereotype.Component;

import javax.annotation.concurrent.Immutable;
import java.math.BigDecimal;

import static com.google.common.base.Preconditions.checkNotNull;

@Component
@Immutable
public class ProductCreationValidatorImpl implements ProductCreationValidator {

    public void validate(Product product) {
        checkNotNull(product, "Product not defined");
        checkNotNull(product.getName(), "Product name not defined");
        checkNotNull(product.getPrice(), "Product price not defined");

        final BigDecimal minPrice = BigDecimal.TEN;
        if (product.getPrice().compareTo(minPrice) < 0) {
            throw new ApplicationExceptionDefault("Price cannot be less than " + minPrice.intValue());
        }

        final BigDecimal maxPrice = BigDecimal.valueOf(10000);
        if (product.getPrice().compareTo(maxPrice) > 0) {
            throw new ApplicationExceptionDefault("Price cannot be more than " + maxPrice.intValue());
        }
    }

}
