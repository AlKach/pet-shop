package by.kachanov.shop.service.converter;

import by.kachanov.shop.dto.condition.Greater;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

@Component
public class GreaterConverter extends AbstractConditionConverter<Greater> {

    @Override
    public Criterion doConvert(Greater source) {
        String field = source.getField();
        Object value = convertType(getRootType(), field, source.getValue());
        return Restrictions.gt(getFieldAlias(field), value);
    }
}
