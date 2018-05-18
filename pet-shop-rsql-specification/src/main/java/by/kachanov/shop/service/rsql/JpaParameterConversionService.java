package by.kachanov.shop.service.rsql;

import javax.annotation.PostConstruct;
import java.util.Collection;

import by.kachanov.shop.service.rsql.converters.jpa.JpaParameterConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.stereotype.Component;

@Component
public class JpaParameterConversionService extends DefaultConversionService {

    @Autowired
    private Collection<JpaParameterConverter> jpaParameterConverters;

    @PostConstruct
    void initConverters() {
        jpaParameterConverters.forEach(this::addConverter);
    }
}
