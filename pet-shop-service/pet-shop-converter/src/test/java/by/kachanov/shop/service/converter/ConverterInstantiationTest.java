package by.kachanov.shop.service.converter;

import org.hibernate.criterion.Criterion;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.List;

import static org.junit.Assert.*;

@ContextConfiguration("classpath:context.xml")
public class ConverterInstantiationTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private List<ContextAwareConverter> converters;

    @Test
    public void testConverterInstantiation() {
        for (ContextAwareConverter converter : converters) {
            try {
                ContextAwareConverter newConverter = converter.getClass().newInstance();
                assertNotNull(newConverter);
            } catch (InstantiationException | IllegalAccessException e) {
                fail(converter.getClass().getName() + " can't be instantiated by default constructor");
            }
        }
    }

    @Test(expected = IllegalStateException.class)
    public void testUninstantiatableConverter() {
        ContextAwareConverter uninstantiatableConverter = new UninstantiatableConverter();
        uninstantiatableConverter.withContext(new ConversionContext(null));
    }

    private static class UninstantiatableConverter extends AbstractConditionConverter {

        private UninstantiatableConverter() {}

        @Override
        protected Criterion doConvert(Object source) {
            return null;
        }
    }

}
