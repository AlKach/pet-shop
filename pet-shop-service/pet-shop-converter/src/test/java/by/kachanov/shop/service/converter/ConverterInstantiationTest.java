package by.kachanov.shop.service.converter;

import by.kachanov.shop.config.RepositoryConfig;
import by.kachanov.shop.config.ServiceConfig;
import org.hibernate.criterion.Criterion;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest(classes = {
        RepositoryConfig.class,
        ServiceConfig.class
})
@EnableAutoConfiguration(exclude = {
        JpaRepositoriesAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class
})
@ActiveProfiles("test")
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
        public Criterion convert(Object source) {
            return null;
        }
    }

}
