package by.kachanov.shop.service.converter;

import by.kachanov.shop.dto.condition.And;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AndConverter extends AbstractConditionConverter<And> {

    @Override
    public Criterion doConvert(And source) {
        List<Criterion> subCriteria = source.getConditions().stream()
                .map(condition -> getConversionService().convert(condition, Criterion.class))
                .collect(Collectors.toList());
        return Restrictions.conjunction(subCriteria.toArray(new Criterion[]{}));
    }
}
