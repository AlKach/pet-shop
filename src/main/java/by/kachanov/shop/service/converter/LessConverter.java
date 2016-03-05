package by.kachanov.shop.service.converter;

import by.kachanov.shop.dto.condition.Less;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

@Component
public class LessConverter extends AbstractConditionConverter<Less> {

    @Override
    public Criterion doConvertCondition(Less less) {
        return Restrictions.lt(less.getField(), less.getValue());
    }

}
