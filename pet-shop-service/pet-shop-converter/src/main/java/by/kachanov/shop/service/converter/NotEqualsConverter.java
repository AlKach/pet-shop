package by.kachanov.shop.service.converter;

import by.kachanov.shop.dto.condition.NotEquals;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

@Component
public class NotEqualsConverter extends AbstractConditionConverter<NotEquals> {
    @Override
    public Criterion convert(NotEquals source) {
        String field = source.getField();
        Object value = convertType(getRootType(), field, source.getValue());
        return Restrictions.ne(getFieldAlias(field), value);
    }
}
