package by.kachanov.shop.service.converter;

import by.kachanov.shop.dto.condition.Between;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BetweenConverter extends AbstractConditionConverter implements Converter<Between, Criterion> {

    @Override
    public Criterion convert(Between source) {
        String field = source.getField();
        Object low = convertType(field, source.getLow());
        Object high = convertType(field, source.getHigh());
        return Restrictions.between(field, low, high);
    }
}
