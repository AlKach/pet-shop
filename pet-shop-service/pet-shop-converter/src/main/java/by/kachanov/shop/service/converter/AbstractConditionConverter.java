package by.kachanov.shop.service.converter;

import org.springframework.core.convert.ConversionService;

public abstract class AbstractConditionConverter<S> implements ContextAwareConverter<S> {

    private ConversionContext conversionContext;

    @Override
    public ContextAwareConverter withContext(ConversionContext conversionContext) {
        try {
            AbstractConditionConverter converter = this.getClass().newInstance();
            converter.conversionContext = conversionContext;
            return converter;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

    protected ConversionService getConversionService() {
        return conversionContext.getConversionService();
    }

    protected Class<?> getRootType() {
        return conversionContext.getRootType();
    }

    protected String getFieldAlias(String field) {
        return ConversionUtils.getFieldAlias(field, conversionContext);
    }

    protected Object convertType(Class<?> rootType, String propertyName, Object originalValue) {
        return ConversionUtils.convertType(rootType, propertyName, originalValue);
    }

}
