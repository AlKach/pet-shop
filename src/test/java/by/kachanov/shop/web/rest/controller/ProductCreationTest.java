package by.kachanov.shop.web.rest.controller;

import by.kachanov.shop.dto.Category;
import by.kachanov.shop.dto.Product;
import org.flywaydb.core.internal.util.Pair;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ProductCreationTest extends AbstractEntityCreationTest {

    private static final String TEST_PRODUCT_NAME = "test product";

    private static final String TEST_PRODUCT_DESCRIPTION = "test description";
    
    private static final BigDecimal TEST_PRODUCT_PRICE = BigDecimal.valueOf(12.3);

    private static final String TEST_CATEGORY_NAME = "test category";

    @Test
    public void testEntityCreation() throws Exception {
        doTestEntityCreation();
    }

    @Override
    protected Object getEntity() {
        Product product = new Product();
        product.setName(TEST_PRODUCT_NAME);
        product.setDescription(TEST_PRODUCT_DESCRIPTION);
        product.setPrice(TEST_PRODUCT_PRICE);
        Category category = new Category();
        category.setName(TEST_CATEGORY_NAME);
        product.setCategories(Collections.singleton(category));
        return product;
    }

    @Override
    protected String getBasePath() {
        return "/products";
    }

    @Override
    protected List<Pair<String, Object>> getValidations() {
        return Arrays.asList(
                Pair.of("$.name", TEST_PRODUCT_NAME),
                Pair.of("$.description", TEST_PRODUCT_DESCRIPTION),
                Pair.of("$.price", TEST_PRODUCT_PRICE.doubleValue()),
                Pair.of("$.categories.length()", 1),
                Pair.of("$.categories[0].name", TEST_CATEGORY_NAME));
    }
}
