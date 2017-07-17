package by.kachanov.shop.service.converter;

import by.kachanov.shop.SpringTestSupport;
import org.hibernate.criterion.Criterion;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class ConverterInstantiationTest extends SpringTestSupport {

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
