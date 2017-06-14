package by.kachanov.shop.service.converter;

import by.kachanov.shop.SpringTest;
import by.kachanov.shop.dto.Order;
import by.kachanov.shop.dto.Product;
import by.kachanov.shop.dto.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.math.BigDecimal;

import static by.kachanov.shop.TestConstants.NUMBER_STRING;
import static org.junit.Assert.assertEquals;

public class AbstractConditionConverterTest extends SpringTest {

    @Autowired
    @Qualifier("dummyConverter")
    private AbstractConditionConverter dummyConverter;

    @Test
    public void testStringConverter() throws Exception {
        testTypeConversion(User.class, "name", NUMBER_STRING, String.class);
    }

    @Test
    public void testBigDecimalConverter() throws Exception {
        testTypeConversion(User.class, "id", NUMBER_STRING, BigDecimal.class);
    }

    @Test
    public void testNestedStringConverter() throws Exception {
        testTypeConversion(Order.class, "user.name", NUMBER_STRING, String.class);
    }

    @Test
    public void testNestedBigDecimalConverter() throws Exception {
        testTypeConversion(Order.class, "user.id", NUMBER_STRING, BigDecimal.class);
    }

    @Test
    public void testCollectionStringConverter() throws Exception {
        testTypeConversion(Product.class, "categories.name", NUMBER_STRING, String.class);
    }

    @Test
    public void testCollectionBigDecimalConverter() throws Exception {
        testTypeConversion(Product.class, "categories.id", NUMBER_STRING, BigDecimal.class);
    }

    @Test
    public void testNestedCollectionBigDecimalConverter() throws Exception {
        testTypeConversion(Order.class, "orderPositions.product.categories.id", NUMBER_STRING, BigDecimal.class);
    }

    private void testTypeConversion(Class targetClass, String propertyName, Object propertyValue, Class expectedClass) {
        ConversionContextHolder.getInstance().setCurrentType(targetClass);
        Object result = dummyConverter.convertType(propertyName, propertyValue);
        assertEquals(expectedClass, result.getClass());
        ConversionContextHolder.getInstance().setCurrentType(null);
    }

}