package by.kachanov.shop.service.rsql;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;

import by.kachanov.shop.config.TestConfig;
import by.kachanov.shop.dto.Order;
import cz.jirutka.rsql.parser.ast.ComparisonNode;
import cz.jirutka.rsql.parser.ast.ComparisonOperator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = TestConfig.class)
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration
@ActiveProfiles("test")
public class RSQLPredicateVisitorTest {
    
    @Autowired
    private ApplicationContext applicationContext;
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Test
    public void testIllegalOperator() {
        assertThrows(
                IllegalArgumentException.class,
                () -> getVisitor(Order.class).visit(createComparison("=illegal=", "user.name")));
    }
    
    private RSQLPredicateVisitor getVisitor(Class<?> rootType) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        Root<?> root = criteriaBuilder.createQuery(rootType).from(rootType);
        RSQLPredicateVisitor visitor = new RSQLPredicateVisitor(root, criteriaBuilder);
        applicationContext.getAutowireCapableBeanFactory().autowireBean(visitor);
        return visitor;
    }

    private ComparisonNode createComparison(String operator, String selector, List<String> arguments) {
        return new ComparisonNode(new ComparisonOperator(operator), selector, arguments);
    }

    private ComparisonNode createComparison(String operator, String selector) {
        return createComparison(operator, selector, Collections.singletonList("default"));
    }
    
}
