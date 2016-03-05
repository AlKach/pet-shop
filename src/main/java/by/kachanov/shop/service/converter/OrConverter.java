package by.kachanov.shop.service.converter;

import by.kachanov.shop.dto.condition.Expression;
import by.kachanov.shop.dto.condition.Or;
import by.kachanov.shop.service.ExpressionConversionService;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrConverter extends AbstractConditionConverter<Or> {

    @Override
    public Criterion doConvertCondition(Or condition) {
        ExpressionConversionService expressionConverter = getExpressionConverter();
        List<Criterion> subCriteria = condition.getExpressions().stream()
                .map(Expression::getActiveCondition)
                .map(expressionConverter::convertCondition)
                .collect(Collectors.toList());
        return Restrictions.disjunction(subCriteria.toArray(new Criterion[]{}));
    }

}
