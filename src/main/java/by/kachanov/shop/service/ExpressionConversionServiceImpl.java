package by.kachanov.shop.service;

import by.kachanov.shop.dto.condition.Expression;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

@Service
public class ExpressionConversionServiceImpl implements ExpressionConversionService {

    public Criteria convertExpression(Class<?> type, Expression expression, Session session) {
        return session.createCriteria(type);
    }
}
