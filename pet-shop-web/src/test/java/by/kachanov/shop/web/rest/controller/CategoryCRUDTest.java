package by.kachanov.shop.web.rest.controller;

import by.kachanov.shop.dto.Category;
import org.flywaydb.core.internal.util.Pair;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

public class CategoryCRUDTest extends AbstractEntityCRUDTest {

    private static final String CATEGORY_NAME = "test category";

    @Test
    public void testEntityCreation() throws Exception {
        doTestEntityCRUD();
    }

    @Override
    protected Object getEntity() {
        Category category = new Category();
        category.setName(CATEGORY_NAME);
        return category;
    }

    @Override
    protected String getBasePath() {
        return "/rest/categories";
    }

    @Override
    protected List<Pair<String, Object>> getValidations() {
        return Collections.singletonList(Pair.of("$.name", CATEGORY_NAME));
    }
}