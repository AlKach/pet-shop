package by.kachanov.shop.service.converter;

import org.hibernate.criterion.Criterion;
import org.springframework.core.convert.converter.Converter;

public interface ContextAwareConverter<SourceClass> extends Converter<SourceClass, Criterion> {

    ContextAwareConverter withContext(ConversionContext conversionContext);

}
