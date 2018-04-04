package by.kachanov.shop.service.converter;

import by.kachanov.shop.dto.condition.Less;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

@Component
public class LessConverter extends AbstractConditionConverter<Less> {

    @Override
    public Criterion convert(Less source) {
        String field = source.getField();
        Object value = convertType(getRootType(), field, source.getValue());
        return Restrictions.lt(getFieldAlias(field), value);
    }
}
