package by.kachanov.shop.service.converter;

import by.kachanov.shop.dto.condition.In;
import org.hibernate.Criteria;
import org.springframework.stereotype.Component;

@Component
public class InConverter extends AbstractConditionConverter<In> {

    @Override
    public Criteria doConvertCondition(Criteria baseCriteria, In condition) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
