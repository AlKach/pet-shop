package by.kachanov.shop.service.converter;

import by.kachanov.shop.dto.condition.Greater;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GreaterConverter extends AbstractConditionConverter implements Converter<Greater, Criterion> {

    @Override
    public Criterion convert(Greater source) {
        String field = source.getField();
        Object value = convertType(field, source.getValue());
        return Restrictions.gt(field, value);
    }
}
