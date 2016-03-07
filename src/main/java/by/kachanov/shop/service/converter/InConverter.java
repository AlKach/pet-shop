package by.kachanov.shop.service.converter;

import by.kachanov.shop.dto.condition.In;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class InConverter implements Converter<In, Criterion> {

    @Override
    public Criterion convert(In source) {
        return Restrictions.in(source.getField(), source.getValues());
    }
}
