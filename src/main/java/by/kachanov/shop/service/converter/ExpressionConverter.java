package by.kachanov.shop.service.converter;

import by.kachanov.shop.dto.condition.Condition;
import by.kachanov.shop.dto.condition.Expression;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Aliaksandr_Kachanov
 */
@Component
public class ExpressionConverter implements Converter<Expression, Criterion> {

    @Autowired
    private ConversionService conversionService;

    @Override
    public Criterion convert(Expression source) {
        Condition activeCondition = source.getActiveCondition();
        return activeCondition != null ? conversionService.convert(activeCondition, Criterion.class) : null;
    }

}
