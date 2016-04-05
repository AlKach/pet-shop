package by.kachanov.shop.service.converter;

import by.kachanov.shop.dto.condition.Or;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrConverter extends AbstractConditionConverter implements Converter<Or, Criterion> {

    @Override
    public Criterion convert(Or source) {
        List<Criterion> subCriteria = source.getConditions().stream()
                .map(condition -> getConverter().convert(condition, Criterion.class))
                .collect(Collectors.toList());
        return Restrictions.disjunction(subCriteria.toArray(new Criterion[]{}));
    }
}
