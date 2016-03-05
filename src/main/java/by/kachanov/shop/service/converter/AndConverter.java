package by.kachanov.shop.service.converter;

import by.kachanov.shop.dto.condition.And;
import by.kachanov.shop.dto.condition.Expression;
import by.kachanov.shop.service.ExpressionConversionService;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AndConverter extends AbstractConditionConverter<And> {

    @Override
    public Criterion doConvertCondition(And and) {
        ExpressionConversionService expressionConverter = getExpressionConverter();
        List<Criterion> subCriteria = and.getExpressions().stream()
                .map(Expression::getActiveCondition)
                .map(expressionConverter::convertCondition)
                .collect(Collectors.toList());
        return Restrictions.conjunction(subCriteria.toArray(new Criterion[]{}));
    }

}
