package by.kachanov.shop.service;

import by.kachanov.shop.dto.condition.*;
import by.kachanov.shop.service.converter.ConditionConverter;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpressionConversionServiceImpl implements ExpressionConversionService {

    @Autowired
    private List<ConditionConverter> conditionConverters;

    public Criteria convertExpression(Class<?> type, Expression expression, Session session) {
        Criteria baseCriteria = session.createCriteria(type);
        if (expression == null || expression.getActiveCondition() == null) {
            return baseCriteria;
        } else {
            return convertCondition(baseCriteria, expression.getActiveCondition());
        }
    }

    private Criteria convertCondition(Criteria baseCriteria, Condition condition) {
        for (ConditionConverter converter : conditionConverters) {
            if (converter.supports(condition.getClass())) {
                return converter.convertCondition(baseCriteria, condition);
            }
        }

        throw new IllegalArgumentException("Unsupported condition type");
    }
}
