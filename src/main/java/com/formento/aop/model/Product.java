package com.formento.aop.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public class Product implements Serializable {

    private final String name;
    private final BigDecimal price;

    @JsonCreator
    public Product(@JsonProperty("name") String name, @JsonProperty("price") BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public BigDecimal getPrice() {
        return price;
    }
}
