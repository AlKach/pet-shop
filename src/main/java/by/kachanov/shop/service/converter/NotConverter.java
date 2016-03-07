package by.kachanov.shop.service.converter;

import by.kachanov.shop.dto.condition.Condition;
import by.kachanov.shop.dto.condition.Not;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NotConverter implements Converter<Not, Criterion> {

    @Autowired
    @Qualifier("conditionConverter")
    private ConversionService expressionConverter;

    @Override
    public Criterion convert(Not source) {
        Condition activeCondition = source.getExpression().getActiveCondition();
        return Restrictions.not(expressionConverter.convert(activeCondition, Criterion.class));
    }
}
