package by.kachanov.shop.service.converter;

import by.kachanov.shop.dto.condition.In;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

@Component
public class InConverter extends AbstractConditionConverter<In> {

    @Override
    public Criterion doConvert(In source) {
        String field = source.getField();
        Object[] values = source.getValues().stream().map(val -> convertType(getRootType(), field, val)).toArray();
        return Restrictions.in(getFieldAlias(field), values);
    }
}
