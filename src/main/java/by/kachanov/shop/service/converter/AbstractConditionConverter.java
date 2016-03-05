package by.kachanov.shop.service.converter;

import by.kachanov.shop.dto.condition.Condition;
import java.lang.reflect.ParameterizedType;

import by.kachanov.shop.service.ExpressionConversionService;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractConditionConverter<T extends Condition> implements ConditionConverter {

    private final Class<?> supportedType;

    @Autowired
    private ExpressionConversionService expressionConverter;

    public AbstractConditionConverter() {
        this.supportedType = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public boolean supports(Class<?> type) {
        return supportedType.isAssignableFrom(type);
    }

    @Override
    public final Criterion convertCondition(Condition condition) {
        if (supports(condition.getClass())) {
            return doConvertCondition((T) condition);
        }
        throw new IllegalArgumentException("Unsupported condition type");
    }

    public ExpressionConversionService getExpressionConverter() {
        return expressionConverter;
    }

    public abstract Criterion doConvertCondition(T condition);

}
