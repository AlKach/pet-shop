package by.kachanov.shop.service.rsql;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import by.kachanov.shop.config.TestConfig;
import by.kachanov.shop.dto.Category;
import by.kachanov.shop.dto.Order;
import by.kachanov.shop.dto.OrderPosition;
import by.kachanov.shop.dto.Product;
import by.kachanov.shop.dto.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestConfig.class)
@EnableAutoConfiguration
@Transactional
@ActiveProfiles("test")
public class SpecificationServiceTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private SpecificationService specificationService;

    @Test
    public void testSpecificationCreation() {
        SpecificationService specificationService = spy(this.specificationService);
        String query = "user.name == 'User 1'";
        Specification<Order> specification = specificationService.buildSpecification(query);
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
        Root<Order> root = criteriaQuery.from(Order.class);
        specification.toPredicate(root, criteriaQuery, criteriaBuilder);
        verify(specificationService).buildPredicate(query, root, criteriaBuilder);
    }

    @Test
    public void testEqualsCriteria() {
        CriteriaQuery<Order> query =
                buildCriteriaQuery("orderPositions.product.categories.id == 11", Order.class, entityManager);
        List<Order> orders = entityManager.createQuery(query).getResultList();
        for (Order order : orders) {
            assertTrue(order.getOrderPositions()
                    .stream()
                    .map(OrderPosition::getProduct)
                    .map(Product::getCategories)
                    .flatMap(Collection::stream)
                    .map(Category::getId)
                    .anyMatch(BigInteger.valueOf(11)::equals));
        }
    }

    @Test
    public void testNotEqualsCriteria() {
        CriteriaQuery<User> query = buildCriteriaQuery("name != 'User 1'", User.class, entityManager);
        List<User> users = entityManager.createQuery(query).getResultList();
        assertTrue(users.stream().map(User::getName).noneMatch("User 1"::equals));
    }
    
    @Test
    public void testGreaterCriteria() {
        CriteriaQuery<OrderPosition> query = buildCriteriaQuery("quantity > 2", OrderPosition.class, entityManager);
        List<OrderPosition> orderPositions = entityManager.createQuery(query).getResultList();
        assertTrue(
                orderPositions.stream().map(OrderPosition::getQuantity).noneMatch(
                        q -> q.compareTo(BigDecimal.valueOf(2)) <= 0));
    }

    @Test
    public void testGreaterOrEqualsCriteria() {
        CriteriaQuery<OrderPosition> query = buildCriteriaQuery("quantity >= 2", OrderPosition.class, entityManager);
        List<OrderPosition> orderPositions = entityManager.createQuery(query).getResultList();
        assertTrue(
                orderPositions.stream().map(OrderPosition::getQuantity).noneMatch(
                        q -> q.compareTo(BigDecimal.valueOf(2)) < 0));
    }

    @Test
    public void testLessCriteria() {
        CriteriaQuery<OrderPosition> query = buildCriteriaQuery("quantity < 3", OrderPosition.class, entityManager);
        List<OrderPosition> orderPositions = entityManager.createQuery(query).getResultList();
        assertTrue(
                orderPositions.stream().map(OrderPosition::getQuantity).noneMatch(
                        q -> q.compareTo(BigDecimal.valueOf(3)) >= 0));
    }
    
    @Test
    public void testLessOrEqualsCriteria() {
        CriteriaQuery<OrderPosition> query = buildCriteriaQuery("quantity <= 3", OrderPosition.class, entityManager);
        List<OrderPosition> orderPositions = entityManager.createQuery(query).getResultList();
        assertTrue(
                orderPositions.stream().map(OrderPosition::getQuantity).noneMatch(
                        q -> q.compareTo(BigDecimal.valueOf(3)) > 0));
    }
    
    @Test
    public void testLikeCriteria() {
        CriteriaQuery<Product> query = buildCriteriaQuery("categories.name =lk= 'Cat*'", Product.class, entityManager);
        List<Product> products = entityManager.createQuery(query).getResultList();
        for (Product product : products) {
            assertTrue(product.getCategories().stream().map(Category::getName).anyMatch(s -> s.matches("Cat.*")));
        }
    }
    
    @Test
    public void testNotLikeCriteria() {
        CriteriaQuery<User> query = buildCriteriaQuery("name =nlk= '*1'", User.class, entityManager);
        List<User> users = entityManager.createQuery(query).getResultList();
        assertTrue(users.stream().map(User::getName).noneMatch(s -> s.matches(".*1")));
    }
    
    @Test
    public void testInCriteria() {
        CriteriaQuery<User> query = buildCriteriaQuery("name =in= ('User 2', 'User 4')", User.class, entityManager);
        List<User> users = entityManager.createQuery(query).getResultList();
        List<String> validNames = Arrays.asList("User 2", "User 4");
        assertTrue(users.stream().map(User::getName).allMatch(validNames::contains));
    }

    @Test
    public void testNotInCriteria() {
        CriteriaQuery<User> query = buildCriteriaQuery("name =out= ('User 1', 'User 3')", User.class, entityManager);
        List<User> users = entityManager.createQuery(query).getResultList();
        List<String> invalidNames = Arrays.asList("User 1", "User 3");
        assertTrue(users.stream().map(User::getName).noneMatch(invalidNames::contains));
    }
    
    @Test
    public void testBetweenCriteria() {
        CriteriaQuery<Order> query = buildCriteriaQuery("orderPositions.quantity =bw= (2, 4)", Order.class, entityManager);
        List<Order> orders = entityManager.createQuery(query).getResultList();
        for (Order order : orders) {
            assertTrue(
                    order.getOrderPositions().stream().map(OrderPosition::getQuantity).anyMatch(
                            q -> BigDecimal.valueOf(2).compareTo(q) <= 0 && BigDecimal.valueOf(4).compareTo(q) >= 0));
        }
    }

    @Test
    public void testNotBetweenCriteria() {
        CriteriaQuery<Order> query = buildCriteriaQuery("orderPositions.quantity =nbw= (2, 4)", Order.class, entityManager);
        List<Order> orders = entityManager.createQuery(query).getResultList();
        for (Order order : orders) {
            assertTrue(
                    order.getOrderPositions().stream().map(OrderPosition::getQuantity).anyMatch(
                            q -> BigDecimal.valueOf(2).compareTo(q) > 0 || BigDecimal.valueOf(4).compareTo(q) < 0));
        }
    }
    
    @Test
    public void testAndCriteria() {
        CriteriaQuery<Product> query =
                buildCriteriaQuery("price >= 34 and categories.name == 'Category 2'", Product.class, entityManager);
        List<Product> products = entityManager.createQuery(query).getResultList();
        for (Product product : products) {
            assertTrue(BigDecimal.valueOf(20).compareTo(product.getPrice()) <= 0);
            assertTrue(product.getCategories().stream().map(Category::getName).anyMatch("Category 2"::equals));
        }
    }
    
    @Test
    public void testOrCriteria() {
        CriteriaQuery<Product> query =
                buildCriteriaQuery("name == 'Product 2' or price < 20", Product.class, entityManager);
        List<Product> products = entityManager.createQuery(query).getResultList();
        for (Product product : products) {
            boolean correctName = "Product 2".equals(product.getName());
            boolean correctPrice = BigDecimal.valueOf(20).compareTo(product.getPrice()) > 0;
            assertTrue(correctName || correctPrice);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalOperator() {
        buildCriteriaQuery("name =illegal= 'Category 1'", Category.class, entityManager);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidProperty() {
        buildCriteriaQuery("invalidProperty == 0", Product.class, entityManager);
    }

    @Test
    public void testEmptyQuery() {
        CriteriaQuery<Category> query = buildCriteriaQuery("", Category.class, entityManager);
        List<Category> categories = entityManager.createQuery(query).getResultList();
        assertEquals(2, categories.size());
    }

    private <T> CriteriaQuery<T> buildCriteriaQuery(String query, Class<T> rootType, EntityManager entityManager) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(rootType);
        Root<T> queryRoot = criteriaQuery.from(rootType);
        Predicate predicate = specificationService.buildPredicate(query, queryRoot, criteriaBuilder);
        criteriaQuery = criteriaQuery.where(predicate);
        return criteriaQuery;
    }

}
