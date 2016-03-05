package by.kachanov.shop.service.converter;

import by.kachanov.shop.dto.condition.Or;
import org.hibernate.Criteria;
import org.springframework.stereotype.Component;

@Component
public class OrConverter extends AbstractConditionConverter<Or> {

    @Override
    public Criteria doConvertCondition(Criteria baseCriteria, Or condition) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
