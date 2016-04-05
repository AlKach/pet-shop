package by.kachanov.shop.service.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;

public abstract class AbstractConditionConverter {

    @Autowired
    @Qualifier("conditionConverter")
    private ConversionService converter;

    protected ConversionService getConverter() {
        return converter;
    }

    protected Object convertType(String propertyName, Object originalValue) {
        Class<?> objectType = ConversionContextHolder.getInstance().getCurrentType();
        if (objectType != null) {
            Class<?> propertyType = resolvePropertyType(objectType, propertyName);
            if (!Object.class.equals(propertyType)) {
                try {
                    return converter.convert(originalValue, propertyType);
                } catch (Exception ex) {
                    return originalValue;
                }
            }
        }

        return originalValue;
    }

    private Class<?> resolvePropertyType(Class<?> targetClass, String propertyName) {
        int dotPosition = propertyName.indexOf(".");
        String directPropertyName = dotPosition == -1 ? propertyName : propertyName.substring(0, dotPosition);
        Class<?> directPropertyType = resolveDirectPropertyType(targetClass, directPropertyName);
        if (directPropertyType.isArray()) {
            directPropertyType = directPropertyType.getComponentType();
        } else if (Collection.class.isAssignableFrom(directPropertyType)) {
            directPropertyType = resolveCollectionType(targetClass, directPropertyName);
        }

        if (dotPosition != -1) {
            return resolvePropertyType(directPropertyType, propertyName.substring(dotPosition + 1));
        } else {
            return directPropertyType;
        }
    }

    private Class<?> resolveDirectPropertyType(Class<?> targetClass, String propertyName) {
        return BeanUtils.findPropertyType(propertyName, targetClass);
    }

    private Class<?> resolveCollectionType(Class<?> targetClass, String propertyName) {
        try {
            Field field = targetClass.getDeclaredField(propertyName);
            ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
            return (Class<?>) parameterizedType.getActualTypeArguments()[0];
        } catch (NoSuchFieldException | ClassCastException e) {
            return Object.class;
        }
    }

}
