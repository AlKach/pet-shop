package by.kachanov.shop.service.converter;

import by.kachanov.shop.dto.condition.Not;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

@Component
public class NotConverter extends AbstractConditionConverter<Not> {

    @Override
    public Criterion doConvert(Not source) {
        return Restrictions.not(getConversionService().convert(source.getCondition(), Criterion.class));
    }
}
