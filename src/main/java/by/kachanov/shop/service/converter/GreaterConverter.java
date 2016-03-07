package by.kachanov.shop.service.converter;

import by.kachanov.shop.dto.condition.Greater;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GreaterConverter implements Converter<Greater, Criterion> {

    @Override
    public Criterion convert(Greater source) {
        return Restrictions.gt(source.getField(), source.getValue());
    }
}
