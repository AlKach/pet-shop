package by.kachanov.shop.service.converter;

import by.kachanov.shop.dto.condition.Between;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

@Component
public class BetweenConverter extends AbstractConditionConverter<Between> {

    @Override
    public Criterion convert(Between source) {
        String field = source.getField();
        Object low = convertType(getRootType(), field, source.getLow());
        Object high = convertType(getRootType(), field, source.getHigh());
        return Restrictions.between(getFieldAlias(field), low, high);
    }
}
