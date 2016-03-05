package by.kachanov.shop.service.converter;

import by.kachanov.shop.dto.condition.Less;
import org.hibernate.Criteria;
import org.springframework.stereotype.Component;

@Component
public class LessConverter extends AbstractConditionConverter<Less> {

    @Override
    public Criteria doConvertCondition(Criteria baseCriteria, Less condition) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
