package by.kachanov.shop.service.converter;

import by.kachanov.shop.dto.condition.Equals;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

@Component
public class EqualsConverter extends AbstractConditionConverter<Equals>{

    @Override
    public Criterion doConvertCondition(Equals eq) {
        return Restrictions.eq(eq.getField(), eq.getValue());
    }

}
