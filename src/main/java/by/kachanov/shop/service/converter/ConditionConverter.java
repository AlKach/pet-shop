package by.kachanov.shop.service.converter;

import by.kachanov.shop.dto.condition.Condition;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Component;

@Component
public interface ConditionConverter {

    boolean supports(Class<?> type);

    Criterion convertCondition(Condition condition);

}
