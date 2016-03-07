package by.kachanov.shop.service.converter;

import by.kachanov.shop.dto.condition.Between;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BetweenConverter implements Converter<Between, Criterion> {

    @Override
    public Criterion convert(Between source) {
        return Restrictions.between(source.getField(), source.getLow(), source.getHigh());
    }
}
