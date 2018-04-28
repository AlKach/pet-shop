package by.kachanov.shop.service.converter;

import org.hibernate.criterion.Criterion;
import org.springframework.core.convert.converter.Converter;

public interface ContextAwareConverter<S> extends Converter<S, Criterion> {

    ContextAwareConverter withContext(ConversionContext conversionContext);

}
