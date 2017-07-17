package by.kachanov.shop.web.rest.controller;

import by.kachanov.shop.dto.Category;
import org.flywaydb.core.internal.util.Pair;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

public class CategoryCreationTest extends AbstractEntityCreationTest {

    private static final String CATEGORY_NAME = "test category";

    @Test
    public void testEntityCreation() throws Exception {
        doTestEntityCreation();
    }

    @Override
    protected Object getEntity() {
        Category category = new Category();
        category.setName(CATEGORY_NAME);
        return category;
    }

    @Override
    protected String getBasePath() {
        return "/categories";
    }

    @Override
    protected List<Pair<String, Object>> getValidations() {
        return Collections.singletonList(Pair.of("$.name", CATEGORY_NAME));
    }
}