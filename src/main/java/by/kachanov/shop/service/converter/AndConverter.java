package by.kachanov.shop.service.converter;

import by.kachanov.shop.dto.condition.And;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AndConverter extends AbstractConditionConverter implements Converter<And, Criterion> {

    @Override
    public Criterion convert(And source) {
        List<Criterion> subCriteria = source.getConditions().stream()
                .map(condition -> getConverter().convert(condition, Criterion.class))
                .collect(Collectors.toList());
        return Restrictions.conjunction(subCriteria.toArray(new Criterion[]{}));
    }
}
