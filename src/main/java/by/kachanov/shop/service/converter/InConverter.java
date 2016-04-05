package by.kachanov.shop.service.converter;

import by.kachanov.shop.dto.condition.In;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InConverter extends AbstractConditionConverter implements Converter<In, Criterion> {

    @Override
    public Criterion convert(In source) {
        String field = source.getField();
        Object[] values = source.getValues().stream().map(val -> convertType(field, val)).toArray();
        return Restrictions.in(field, values);
    }
}
