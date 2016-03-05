package by.kachanov.shop.service;

import by.kachanov.shop.dto.condition.Condition;
import by.kachanov.shop.dto.condition.Expression;
import org.hibernate.criterion.Criterion;

public interface ExpressionConversionService {

    Criterion convertExpression(Expression expression);

    Criterion convertCondition(Condition condition);

}
