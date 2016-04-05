package by.kachanov.shop.service.converter;

import by.kachanov.shop.dto.condition.Not;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NotConverter extends AbstractConditionConverter implements Converter<Not, Criterion> {

    @Override
    public Criterion convert(Not source) {
        return Restrictions.not(getConverter().convert(source.getCondition(), Criterion.class));
    }
}
