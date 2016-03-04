package by.kachanov.shop.service;

import by.kachanov.shop.dto.condition.Expression;
import org.hibernate.Criteria;
import org.hibernate.Session;

public interface ExpressionConversionService {

    Criteria convertExpression(Class<?> type, Expression expression, Session session);

}
