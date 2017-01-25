package com.formento.aop.service.impl;

import com.formento.aop.model.Product;
import com.formento.aop.service.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.concurrent.Immutable;

@Service
@Immutable
public class ProductServiceImpl implements ProductService {

    @Override
    public Product create(Product product) {
        return product;
    }

}
