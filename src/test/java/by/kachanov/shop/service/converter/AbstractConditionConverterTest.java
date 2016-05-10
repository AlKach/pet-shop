package by.kachanov.shop.service.converter;

import by.kachanov.shop.SpringTest;
import by.kachanov.shop.dto.Order;
import by.kachanov.shop.dto.Product;
import by.kachanov.shop.dto.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class AbstractConditionConverterTest extends SpringTest {

    @Autowired
    @Qualifier("dummyConverter")
    private AbstractConditionConverter dummyConverter;

    @Test
    public void testStringConverter() throws Exception {
        testTypeConversion(User.class, "name", "12345", String.class);
    }

    @Test
    public void testBigDecimalConverter() throws Exception {
        testTypeConversion(User.class, "id", "12345", BigDecimal.class);
    }

    @Test
    public void testNestedStringConverter() throws Exception {
        testTypeConversion(Order.class, "user.name", "12345", String.class);
    }

    @Test
    public void testNestedBigDecimalConverter() throws Exception {
        testTypeConversion(Order.class, "user.id", "12345", BigDecimal.class);
    }

    @Test
    public void testCollectionStringConverter() throws Exception {
        testTypeConversion(Product.class, "categories.name", "12345", String.class);
    }

    @Test
    public void testCollectionBigDecimalConverter() throws Exception {
        testTypeConversion(Product.class, "categories.id", "12345", BigDecimal.class);
    }

    @Test
    public void testNestedCollectionBigDecimalConverter() throws Exception {
        testTypeConversion(Order.class, "orderPositions.product.categories.id", "12345", BigDecimal.class);
    }

    private void testTypeConversion(Class targetClass, String propertyName, Object propertyValue, Class expectedClass) {
        ConversionContextHolder.getInstance().setCurrentType(targetClass);
        Object result = convertType(propertyName, propertyValue);
        assertEquals(expectedClass, result.getClass());
        ConversionContextHolder.getInstance().setCurrentType(null);
    }

    private Object convertType(String propertyName, Object propertyValue) {
        try {
            Method method = AbstractConditionConverter.class
                    .getDeclaredMethod("convertType", String.class, Object.class);
            method.setAccessible(true);
            return method.invoke(dummyConverter, propertyName, propertyValue);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
            fail();
            return null;
        }
    }

}