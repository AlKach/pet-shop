package by.kachanov.shop.service.converter;

import by.kachanov.shop.dto.condition.LessOrEquals;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

@Component
public class LessOrEqualsConverter extends AbstractConditionConverter<LessOrEquals> {
    @Override
    public Criterion convert(LessOrEquals source) {
        String field = source.getField();
        Object value = convertType(getRootType(), field, source.getValue());
        return Restrictions.le(getFieldAlias(field), value);
    }
}
