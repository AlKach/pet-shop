package by.kachanov.shop.service.converter;

import by.kachanov.shop.dto.condition.Equals;
import org.hibernate.Criteria;
import org.springframework.stereotype.Component;

@Component
public class EqualsConverter extends AbstractConditionConverter<Equals>{

    @Override
    public Criteria doConvertCondition(Criteria baseCriteria, Equals condition) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
