package by.kachanov.shop.service.converter;

import by.kachanov.shop.dto.condition.Like;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LikeConverter implements Converter<Like, Criterion> {

    @Override
    public Criterion convert(Like source) {
        return Restrictions.like(source.getField(), source.getValue());
    }
}
