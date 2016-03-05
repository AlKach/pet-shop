package by.kachanov.shop.service.converter;

import by.kachanov.shop.dto.condition.In;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

@Component
public class InConverter extends AbstractConditionConverter<In> {

    @Override
    public Criterion doConvertCondition(In in) {
        return Restrictions.in(in.getField(), in.getValues());
    }

}
