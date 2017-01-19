package com.formento.aop.model.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.formento.aop.model.Product;

import java.math.BigDecimal;

public class ProductTemplate implements TemplateLoader {

    public static final String VALID = "valid";
    public static final String INVALID_MIN = "invalidMin";
    public static final String INVALID_MAX = "invalidMax";

    @Override
    public void load() {
        Fixture.of(Product.class)
                .addTemplate(VALID, new Rule() {{
                    add("name", "Paul");
                    add("price", BigDecimal.valueOf(200));
                }})
                .addTemplate(INVALID_MIN, new Rule() {{
                    add("name", "John");
                    add("price", BigDecimal.valueOf(9));
                }})
                .addTemplate(INVALID_MAX, new Rule() {{
                    add("name", "Peter");
                    add("price", BigDecimal.valueOf(50000));
                }});
    }

}
