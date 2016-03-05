package by.kachanov.shop.service.converter;

import by.kachanov.shop.dto.condition.Like;
import org.hibernate.Criteria;
import org.springframework.stereotype.Component;

@Component
public class LikeConverter extends AbstractConditionConverter<Like> {

    @Override
    public Criteria doConvertCondition(Criteria baseCriteria, Like condition) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
