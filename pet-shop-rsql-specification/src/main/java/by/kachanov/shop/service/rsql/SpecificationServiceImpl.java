package by.kachanov.shop.service.rsql;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.RSQLParserException;
import cz.jirutka.rsql.parser.ast.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public <T> Predicate buildPredicate(String query, Root<T> queryRoot, CriteriaBuilder criteriaBuilder) {
        if (query == null || query.trim().isEmpty()) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        } else {
            Node astRoot;
            try {
                astRoot = new RSQLParser(Operators.ALL_OPERATORS).parse(query);
            } catch (RSQLParserException ex) {
                throw new IllegalArgumentException(ex);
            }
            RSQLPredicateVisitor visitor = new RSQLPredicateVisitor(queryRoot, criteriaBuilder);
            applicationContext.getAutowireCapableBeanFactory().autowireBean(visitor);
            return astRoot.accept(visitor);
        }
    }

    @Override
    public <T> Specification<T> buildSpecification(String query) {
        return (Specification<T>) (root, criteriaQuery, builder) -> buildPredicate(query, root, builder);
    }
}
