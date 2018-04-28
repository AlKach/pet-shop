package by.kachanov.shop.service.rsql;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.RSQLParserException;
import cz.jirutka.rsql.parser.ast.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class RSQLParsingServiceImpl implements RSQLParsingService {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public <T> CriteriaQuery<T> parseQuery(String query, Class<T> rootType, EntityManager entityManager) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(rootType);
        Root<T> queryRoot = criteriaQuery.from(rootType);
        if (query != null && !query.trim().isEmpty()) {
            Node root;
            try {
                root = new RSQLParser(Operators.ALL_OPERATORS).parse(query);
            } catch (RSQLParserException ex) {
                throw new IllegalArgumentException(ex);
            }
            RSQLCriteriaVisitor visitor = new RSQLCriteriaVisitor(queryRoot, criteriaBuilder);
            applicationContext.getAutowireCapableBeanFactory().autowireBean(visitor);
            Predicate predicate = root.accept(visitor);
            criteriaQuery = criteriaQuery.where(predicate);
        }
        return criteriaQuery;
    }
}
