package by.kachanov.shop.service.converter;

import by.kachanov.shop.dto.condition.Or;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrConverter extends AbstractConditionConverter<Or> {

    @Override
    public Criterion doConvert(Or source) {
        List<Criterion> subCriteria = source.getConditions().stream()
                .map(condition -> getConversionService().convert(condition, Criterion.class))
                .collect(Collectors.toList());
        return Restrictions.disjunction(subCriteria.toArray(new Criterion[]{}));
    }
}
