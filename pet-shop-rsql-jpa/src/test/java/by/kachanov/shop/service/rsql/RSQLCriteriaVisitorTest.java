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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(classes = TestConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
@EnableAutoConfiguration(exclude = {
        JpaRepositoriesAutoConfiguration.class
})
@ActiveProfiles("test")
public class RSQLCriteriaVisitorTest {
    
    @Autowired
    private ApplicationContext applicationContext;
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Test(expected = IllegalArgumentException.class)
    public void testIllegalOperator() {
        getVisitor(Order.class).visit(createComparison("=illegal=", "user.name"));
    }
    
    private RSQLCriteriaVisitor getVisitor(Class<?> rootType) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        Root<?> root = criteriaBuilder.createQuery(rootType).from(rootType);
        RSQLCriteriaVisitor visitor = new RSQLCriteriaVisitor(root, criteriaBuilder);
        applicationContext.getAutowireCapableBeanFactory().autowireBean(visitor);
        return visitor;
    }

    private ComparisonNode createComparison(String operator, String selector, List<String> arguments) {
        return new ComparisonNode(new ComparisonOperator(operator), selector, arguments);
    }

    private ComparisonNode createComparison(String operator, String selector) {
        return new ComparisonNode(new ComparisonOperator(operator), selector, Collections.singletonList("default"));
    }
    
}
