package by.kachanov.shop.repository;

import by.kachanov.shop.SpringTest;
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

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;

import static org.junit.Assert.*;

public class AbstractRepositoryTest extends SpringTest {

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

    private User user;
    private Category category;
    private Product product;
    private Order order;

    @Before
    public void setUp() throws Exception {
        user = new User();
        user.setName("user_name");
        user.setLogin("user_login");
        user.setPassword("user_password");
        userService.saveUser(user);

        category = new Category();
        category.setName("category_name");
        categoryService.saveCategory(category);

        product = new Product();
        product.setName("product_name");
        product.setDescription("product_description");
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
        productService.deleteProduct(product.getId());
        categoryService.deleteCategory(category.getId());
        userService.deleteUser(user.getId());
    }

    @Test
    public void testNestedFieldAccess() throws Exception {
        assertNotEquals(repository.getCriteria(Order.class, new Equals("orderPositions.product.categories.name", "category_name")).list().size(), 0);
    }

}
