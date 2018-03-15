package by.kachanov.shop.service.converter;

import by.kachanov.shop.dto.condition.GreaterOrEquals;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

@Component
public class GreaterOrEqualsConverter extends AbstractConditionConverter<GreaterOrEquals> {
    @Override
    protected Criterion doConvert(GreaterOrEquals source) {
        String field = source.getField();
        Object value = convertType(getRootType(), field, source.getValue());
        return Restrictions.ge(getFieldAlias(field), value);
    }
}
