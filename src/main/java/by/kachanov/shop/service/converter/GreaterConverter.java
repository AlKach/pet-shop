package by.kachanov.shop.service.converter;

import by.kachanov.shop.dto.condition.Greater;
import org.hibernate.Criteria;
import org.springframework.stereotype.Component;

@Component
public class GreaterConverter extends AbstractConditionConverter<Greater> {

    @Override
    public Criteria doConvertCondition(Criteria baseCriteria, Greater condition) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
