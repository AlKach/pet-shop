package by.kachanov.shop.service.converter;

import by.kachanov.shop.dto.condition.Like;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

@Component
public class LikeConverter extends AbstractConditionConverter<Like> {

    @Override
    public Criterion doConvertCondition(Like like) {
        return Restrictions.like(like.getField(), like.getValue());
    }

}
