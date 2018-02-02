package by.kachanov.shop.repository;

import by.kachanov.shop.dto.Category;
import by.kachanov.shop.dto.Order;
import by.kachanov.shop.dto.OrderPosition;
import by.kachanov.shop.dto.Product;
import by.kachanov.shop.dto.User;
import by.kachanov.shop.dto.condition.Equals;
import by.kachanov.shop.service.CategoryService;
import by.kachanov.shop.service.OrderService;
import by.kachanov.shop.service.ProductService;
import by.kachanov.shop.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;

import static org.junit.Assert.*;

@ContextConfiguration("classpath:context.xml")
public class AbstractRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

    private static final String USER_NAME = "user_name";
    private static final String USER_LOGIN = "user_login";
    private static final String USER_PASSWORD = "user_password";
    private static final String CATEGORY_NAME = "category_name";
    private static final String PRODUCT_NAME = "product_name";
    private static final String PRODUCT_DESCRIPTION = "product_description";

    @Autowired
    @Qualifier("dummyRepository")
    private AbstractRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    private Order order;

    @Before
    public void setUp() throws Exception {
        User user = new User();
        user.setName(USER_NAME);
        user.setLogin(USER_LOGIN);
        user.setPassword(USER_PASSWORD);
        userService.saveUser(user);

        Category category = new Category();
        category.setName(CATEGORY_NAME);
        categoryService.saveCategory(category);

        Product product = new Product();
        product.setName(PRODUCT_NAME);
        product.setDescription(PRODUCT_DESCRIPTION);
        product.setPrice(BigDecimal.ZERO);
        product.setCategories(Collections.singleton(category));
        productService.saveProduct(product);

        OrderPosition orderPosition = new OrderPosition();
        orderPosition.setProduct(product);
        orderPosition.setQuantity(BigDecimal.ONE);

        order = new Order();
        order.setUser(user);
        order.setDate(new Date());
        order.setOrderPositions(Collections.singleton(orderPosition));
        orderService.saveOrder(order);
    }

    @After
    public void tearDown() throws Exception {
        orderService.deleteOrder(order.getId());
    }

    @Test
    public void testNestedFieldCriteria() throws Exception {
        assertNotEquals(repository.getCriteria(User.class, new Equals("name", USER_NAME)).list().size(), 0);
        assertNotEquals(repository.getCriteria(Product.class, new Equals("categories.name", CATEGORY_NAME)).list().size(), 0);
        assertNotEquals(repository.getCriteria(Order.class, new Equals("orderPositions.product.name", PRODUCT_NAME)).list().size(), 0);
        assertNotEquals(repository.getCriteria(Order.class, new Equals("orderPositions.product.categories.name", CATEGORY_NAME)).list().size(), 0);
    }

}
