package by.kachanov.shop.service.converter;

import by.kachanov.shop.dto.condition.Condition;
import java.lang.reflect.ParameterizedType;
import org.hibernate.Criteria;

public abstract class AbstractConditionConverter<T extends Condition> implements ConditionConverter {

    private final Class<?> supportedType;

    public AbstractConditionConverter() {
        this.supportedType = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public boolean supports(Class<?> type) {
        return supportedType.isAssignableFrom(type);
    }

    @Override
    public final Criteria convertCondition(Criteria baseCriteria, Condition condition) {
        if (supports(condition.getClass())) {
            return doConvertCondition(baseCriteria, (T) condition);
        }
        throw new IllegalArgumentException("Unsupported condition type");
    }

    public abstract Criteria doConvertCondition(Criteria baseCriteria, T condition);

}
