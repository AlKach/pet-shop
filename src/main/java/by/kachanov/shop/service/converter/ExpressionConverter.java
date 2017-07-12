package by.kachanov.shop.service.converter;

import by.kachanov.shop.dto.condition.Condition;
import by.kachanov.shop.dto.condition.Expression;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Component;

/**
 * @author Aliaksandr_Kachanov
 */
@Component
public class ExpressionConverter extends AbstractConditionConverter<Expression> {

    @Override
    public Criterion doConvert(Expression source) {
        Condition activeCondition = source.getActiveCondition();
        return activeCondition != null ? getConversionService().convert(activeCondition, Criterion.class) : null;
    }

}
