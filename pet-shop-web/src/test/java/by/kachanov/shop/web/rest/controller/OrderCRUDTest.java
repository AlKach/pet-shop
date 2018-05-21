package by.kachanov.shop.web.rest.controller;

import by.kachanov.shop.dto.Category;
import by.kachanov.shop.dto.Order;
import by.kachanov.shop.dto.OrderPosition;
import by.kachanov.shop.dto.Product;
import by.kachanov.shop.dto.User;
import by.kachanov.shop.service.api.CategoryService;
import by.kachanov.shop.service.api.ProductService;
import by.kachanov.shop.service.api.UserService;
import org.flywaydb.core.internal.util.Pair;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class OrderCRUDTest extends AbstractEntityCRUDTest {

    private static final String TEST_USER_NAME = "test user";

    private static final String TEST_USER_LOGIN = "test login";

    private static final String TEST_USER_PASSWORD = "test password";

    private static final String TEST_PRODUCT_NAME = "test product";

    private static final String TEST_PRODUCT_DESCRIPTION = "test description";

    private static final BigDecimal TEST_PRODUCT_PRICE = BigDecimal.valueOf(23.4);

    private static final String TEST_CATEGORY_NAME = "test category";

    private static final BigDecimal TEST_QUANTITY = BigDecimal.TEN;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Test
    public void testEntityCreation() throws Exception {
        doTestEntityCRUD();
    }

    @Override
    protected Object getEntity() {
        User user = new User();
        user.setLogin(TEST_USER_LOGIN);
        user.setPassword(TEST_USER_PASSWORD);
        user.setName(TEST_USER_NAME);
        userService.saveUser(user);

        Category category = new Category();
        category.setName(TEST_CATEGORY_NAME);
        categoryService.saveCategory(category);

        Product product = new Product();
        product.setName(TEST_PRODUCT_NAME);
        product.setDescription(TEST_PRODUCT_DESCRIPTION);
        product.setPrice(TEST_PRODUCT_PRICE);
        product.setCategories(Collections.singleton(category));
        productService.saveProduct(product);

        OrderPosition orderPosition = new OrderPosition();
        orderPosition.setProduct(product);
        orderPosition.setQuantity(TEST_QUANTITY);

        Order order = new Order();
        order.setDate(new Date());
        order.setUser(user);
        order.setOrderPositions(Collections.singleton(orderPosition));

        return order;
    }

    @Override
    protected String getBasePath() {
        return "/rest/orders";
    }

    @Override
    protected List<Pair<String, Object>> getValidations() {
        return Arrays.asList(
                Pair.of("$.orderPositions.length()", 1),
                Pair.of("$.orderPositions[0].quantity", TEST_QUANTITY.intValue()),
                Pair.of("$.orderPositions[0].product.name", TEST_PRODUCT_NAME),
                Pair.of("$.orderPositions[0].product.description", TEST_PRODUCT_DESCRIPTION),
                Pair.of("$.orderPositions[0].product.price", TEST_PRODUCT_PRICE.doubleValue()),
                Pair.of("$.orderPositions[0].product.categories.length()", 1),
                Pair.of("$.orderPositions[0].product.categories[0].name", TEST_CATEGORY_NAME),
                Pair.of("$.user.name", TEST_USER_NAME),
                Pair.of("$.user.login", TEST_USER_LOGIN),
                Pair.of("$.user.password", TEST_USER_PASSWORD));
    }
}
