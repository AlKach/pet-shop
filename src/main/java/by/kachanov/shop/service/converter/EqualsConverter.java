package by.kachanov.shop.service.converter;

import by.kachanov.shop.dto.condition.Equals;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EqualsConverter implements Converter<Equals, Criterion> {

    @Override
    public Criterion convert(Equals source) {
        return Restrictions.eq(source.getField(), source.getValue());
    }
}
