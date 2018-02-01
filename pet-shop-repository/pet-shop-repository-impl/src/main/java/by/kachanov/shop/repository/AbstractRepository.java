package by.kachanov.shop.repository;

import by.kachanov.shop.dto.condition.Condition;
import by.kachanov.shop.service.ConditionConversionServiceFactory;
import by.kachanov.shop.service.converter.ConversionContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private ConditionConversionServiceFactory conversionServiceFactory;

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    protected Criteria getCriteria(Class<?> type, Condition condition) {
        Criteria criteria = getCurrentSession().createCriteria(type);
        ConversionContext conversionContext = new ConversionContext(type);
        Criterion criterion =
                conversionServiceFactory.createConverter(conversionContext).convert(condition, Criterion.class);
        if (criterion != null) {
            conversionContext.getRegisteredAliases().forEach(criteria::createAlias);
            criteria.add(criterion);
        }

        return criteria;
    }

}
