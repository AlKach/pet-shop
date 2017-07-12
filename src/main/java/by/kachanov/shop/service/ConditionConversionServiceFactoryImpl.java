package by.kachanov.shop.service;

import by.kachanov.shop.service.converter.AbstractConditionConverter;
import by.kachanov.shop.service.converter.ContextAwareConverter;
import by.kachanov.shop.service.converter.ConversionContext;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("conditionConversionServiceFactory")
public class ConditionConversionServiceFactoryImpl implements ConditionConversionServiceFactory {

    @Autowired
    private List<ContextAwareConverter> converters;

    @Override
    public ConversionService createConverter(ConversionContext conversionContext) {
        DefaultConversionService conversionService = new DefaultConversionService();
        conversionContext.setConversionService(conversionService);
        for (ContextAwareConverter converter : converters) {
            Class<?> sourceType =
                    GenericTypeResolver.resolveTypeArgument(converter.getClass(), AbstractConditionConverter.class);
            conversionService.addConverter(sourceType, Criterion.class, converter.withContext(conversionContext));
        }
        return conversionService;
    }
}
