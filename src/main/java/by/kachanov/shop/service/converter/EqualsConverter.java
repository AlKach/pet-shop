package by.kachanov.shop.service.converter;

import by.kachanov.shop.dto.condition.Equals;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EqualsConverter extends AbstractConditionConverter implements Converter<Equals, Criterion> {

    @Override
    public Criterion convert(Equals source) {
        String field = source.getField();
        Object value = convertType(source.getRootType(), field, source.getValue());
        return Restrictions.eq(getFieldAlias(field), value);
    }
}
