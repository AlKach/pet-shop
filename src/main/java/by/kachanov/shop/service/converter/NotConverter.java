package by.kachanov.shop.service.converter;

import by.kachanov.shop.dto.condition.Not;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

@Component
public class NotConverter extends AbstractConditionConverter<Not> {

    @Override
    public Criterion doConvertCondition(Not not) {
        return Restrictions.not(getExpressionConverter().convertCondition(not.getExpression().getActiveCondition()));
    }

}
