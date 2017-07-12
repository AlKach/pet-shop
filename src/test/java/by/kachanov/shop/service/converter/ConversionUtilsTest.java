package by.kachanov.shop.service.converter;

import by.kachanov.shop.SpringTest;
import by.kachanov.shop.dto.Order;
import by.kachanov.shop.dto.Product;
import by.kachanov.shop.dto.User;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static by.kachanov.shop.TestConstants.NUMBER_STRING;
import static org.junit.Assert.*;

public class ConversionUtilsTest extends SpringTest {

    private static final String FIELD_NAME = "field";

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
        Object result = ConversionUtils.convertType(targetClass, propertyName, propertyValue);
        assertEquals(expectedClass, result.getClass());
    }

    @Test
    public void testGetAliasPlain() throws Exception {
        ConversionContext conversionContext = new ConversionContext(null);
        assertEquals(ConversionUtils.getFieldAlias(FIELD_NAME, conversionContext), FIELD_NAME);
    }

    @Test
    public void testGetAliasNested() throws Exception {
        for (int i = 1; i < 10; i++) {
            String field = IntStream.range(0, i + 1).mapToObj(n -> FIELD_NAME + n).collect(Collectors.joining("."));
            ConversionContext conversionContext = new ConversionContext(null);
            assertEquals(
                    ConversionUtils.getFieldAlias(field, conversionContext), "alias" + (i - 1) + "." + FIELD_NAME + i);
        }
    }

    @Test
    public void testRegisteredAliases() throws Exception {
        String field = "a.b.c.d.e.f.g.h";
        ConversionContext conversionContext = new ConversionContext(null);
        String fieldAlias = ConversionUtils.getFieldAlias(field, conversionContext);
        Map<String, String> registeredAliases = conversionContext.getRegisteredAliases();
        assertEquals(registeredAliases.size(), 7);

        Map<String, String> reversedAliases = new HashMap<>();
        registeredAliases.forEach((k, v) -> reversedAliases.put(v, k));

        boolean replaced;
        do {
            replaced = false;
            for (Map.Entry<String, String> entry : reversedAliases.entrySet()) {
                String alias = entry.getKey();
                String originalField = entry.getValue();
                replaced = replaced || fieldAlias.contains(alias);
                fieldAlias = fieldAlias.replace(alias, originalField);
            }
        } while (replaced);

        assertEquals(field, fieldAlias);
    }

    @Test
    public void testRepeatingAliases() throws Exception {
        testRepeatingAliases(FIELD_NAME);
    }

    @Test
    public void testRepeatingNestedAlias() throws Exception {
        String field = "outer.inner";
        testRepeatingAliases(field);
    }

    private void testRepeatingAliases(String field) {
        ConversionContext conversionContext = new ConversionContext(null);
        String alias1 = ConversionUtils.getFieldAlias(field, conversionContext);
        String alias2 = ConversionUtils.getFieldAlias(field, conversionContext);
        String alias3 = ConversionUtils.getFieldAlias(field, conversionContext);

        assertEquals(alias1, alias2);
        assertEquals(alias1, alias3);
        assertEquals(alias2, alias3);
    }

}