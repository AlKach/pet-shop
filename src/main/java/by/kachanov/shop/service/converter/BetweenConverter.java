package by.kachanov.shop.service.converter;

import by.kachanov.shop.dto.condition.Between;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

@Component
public class BetweenConverter extends AbstractConditionConverter<Between> {

    @Override
    public Criterion doConvertCondition(Between between) {
        return Restrictions.between(between.getField(), between.getLow(), between.getHigh());
    }

}
