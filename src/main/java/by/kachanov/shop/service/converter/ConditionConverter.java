package by.kachanov.shop.service.converter;

import by.kachanov.shop.dto.condition.Condition;
import org.hibernate.Criteria;
import org.springframework.stereotype.Component;

@Component
public interface ConditionConverter {

    boolean supports(Class<?> type);

    Criteria convertCondition(Criteria baseCriteria, Condition condition);

}
