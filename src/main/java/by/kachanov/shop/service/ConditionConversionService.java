package by.kachanov.shop.service;

import by.kachanov.shop.dto.condition.Condition;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service("conditionConverter")
public class ConditionConversionService extends DefaultConversionService {

    @Autowired
    private List<Converter<? extends Condition, Criterion>> converters;

    @PostConstruct
    private void postConstruct() {
        converters.forEach(this::addConverter);
    }

}
