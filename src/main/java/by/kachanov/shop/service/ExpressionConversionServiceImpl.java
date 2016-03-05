package by.kachanov.shop.service;

import by.kachanov.shop.dto.condition.*;
import by.kachanov.shop.service.converter.ConditionConverter;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpressionConversionServiceImpl implements ExpressionConversionService {

    @Autowired
    private List<ConditionConverter> conditionConverters;

    public Criterion convertExpression(Expression expression) {
        if (expression == null || expression.getActiveCondition() == null) {
            return Restrictions.sqlRestriction("");
        } else {
            return convertCondition(expression.getActiveCondition());
        }
    }

    public Criterion convertCondition(Condition condition) {
        for (ConditionConverter converter : conditionConverters) {
            if (converter.supports(condition.getClass())) {
                return converter.convertCondition(condition);
            }
        }

        throw new IllegalArgumentException("Unsupported condition type");
    }
}
