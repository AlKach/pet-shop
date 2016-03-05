package by.kachanov.shop.service.converter;

import by.kachanov.shop.dto.condition.Not;
import org.hibernate.Criteria;
import org.springframework.stereotype.Component;

@Component
public class NotConverter extends AbstractConditionConverter<Not> {

    @Override
    public Criteria doConvertCondition(Criteria baseCriteria, Not condition) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
