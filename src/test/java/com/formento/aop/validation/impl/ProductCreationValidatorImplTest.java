package com.formento.aop.validation.impl;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.formento.aop.exception.impl.ApplicationExceptionDefault;
import com.formento.aop.model.Product;
import com.formento.aop.model.template.ProductTemplate;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProductCreationValidatorImplTest {

    @BeforeClass
    public static void initClass() {
        FixtureFactoryLoader.loadTemplates("com.formento.aop.model.template");
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @InjectMocks
    private ProductCreationValidatorImpl validator;

    @Test
    public void shouldBeAValidValue() {
        // given
        final Product product = Fixture.from(Product.class).gimme(ProductTemplate.VALID);

        // when
        validator.validate(product);
    }

    @Test
    public void shouldValidateMinValue() {
        // given
        final Product product = Fixture.from(Product.class).gimme(ProductTemplate.INVALID_MIN);

        // excpected exception
        expectedException.expect(ApplicationExceptionDefault.class);
        expectedException.expectMessage("Price cannot be less than 10");

        // when
        validator.validate(product);
    }

    @Test
    public void shouldValidateMaxValue() {
        // given
        final Product product = Fixture.from(Product.class).gimme(ProductTemplate.INVALID_MAX);

        // excpected exception
        expectedException.expect(ApplicationExceptionDefault.class);
        expectedException.expectMessage("Price cannot be more than 10000");

        // when
        validator.validate(product);
    }

}
