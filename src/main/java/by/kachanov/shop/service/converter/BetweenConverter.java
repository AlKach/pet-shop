package by.kachanov.shop.service.converter;

import by.kachanov.shop.dto.condition.Between;
import org.hibernate.Criteria;
import org.springframework.stereotype.Component;

@Component
public class BetweenConverter extends AbstractConditionConverter<Between> {

    @Override
    public Criteria doConvertCondition(Criteria baseCriteria, Between condition) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
