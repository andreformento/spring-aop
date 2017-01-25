package com.formento.aop.api.v1.controller;

import com.formento.aop.model.Product;
import com.formento.aop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.concurrent.Immutable;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/api/v1/products")
@Immutable
public class ProductController {

    private final ProductService service;

    @Autowired
    public ProductController(final ProductService service) {
        this.service = service;
    }


    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<Resource<Product>> create(@RequestBody Product product) {
        return new ResponseEntity<>(new Resource<>(service.create(product), linkTo(ProductController.class).withSelfRel()), HttpStatus.CREATED);
    }


}
