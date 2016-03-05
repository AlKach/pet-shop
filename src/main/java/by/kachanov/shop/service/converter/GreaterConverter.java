package by.kachanov.shop.service.converter;

import by.kachanov.shop.dto.condition.Greater;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

@Component
public class GreaterConverter extends AbstractConditionConverter<Greater> {

    @Override
    public Criterion doConvertCondition(Greater greater) {
        return Restrictions.gt(greater.getField(), greater.getValue());
    }

}
