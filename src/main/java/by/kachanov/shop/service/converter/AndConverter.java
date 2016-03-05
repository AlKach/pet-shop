package by.kachanov.shop.service.converter;

import by.kachanov.shop.dto.condition.And;
import org.hibernate.Criteria;
import org.springframework.stereotype.Component;

@Component
public class AndConverter extends AbstractConditionConverter<And> {

    @Override
    public Criteria doConvertCondition(Criteria baseCriteria, And condition) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
