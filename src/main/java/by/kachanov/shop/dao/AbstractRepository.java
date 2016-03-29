package by.kachanov.shop.dao;

import by.kachanov.shop.dto.condition.Condition;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;

public class AbstractRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    @Qualifier("conditionConverter")
    private ConversionService expressionConverter;

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    protected Criteria getCriteria(Class<?> type, Condition condition) {
        Criteria criteria = getCurrentSession().createCriteria(type);
        Criterion criterion = expressionConverter.convert(condition, Criterion.class);
        if (criterion != null) {
            return criteria.add(criterion);
        } else {
            return criteria;
        }
    }

}
