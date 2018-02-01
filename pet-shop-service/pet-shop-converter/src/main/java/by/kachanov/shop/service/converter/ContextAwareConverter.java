package by.kachanov.shop.service.converter;

import org.springframework.core.convert.converter.Converter;

public interface ContextAwareConverter extends Converter {

    ContextAwareConverter withContext(ConversionContext conversionContext);

}
