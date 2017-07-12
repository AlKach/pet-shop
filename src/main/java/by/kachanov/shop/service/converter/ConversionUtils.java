package by.kachanov.shop.service.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.support.DefaultConversionService;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Map;

public class ConversionUtils {

    private static final DefaultConversionService defaultConversionService = new DefaultConversionService();

    public static String getFieldAlias(String field, ConversionContext conversionContext) {
        String resolvedAlias = field;
        Map<String, String> aliases = conversionContext.getRegisteredAliases();

        if (resolvedAlias.contains(".")) {
            int firstDotPosition;
            int secondDotPosition = resolvedAlias.indexOf('.');
            while (secondDotPosition != -1) {
                String fieldForAlias = resolvedAlias.substring(0, secondDotPosition);
                if (!aliases.containsKey(fieldForAlias)) {
                    String alias = "alias" + aliases.size();
                    aliases.put(fieldForAlias, alias);
                    resolvedAlias = alias + resolvedAlias.substring(secondDotPosition);
                } else {
                    resolvedAlias = resolvedAlias.replace(fieldForAlias, aliases.get(fieldForAlias));
                }

                firstDotPosition = resolvedAlias.indexOf('.');
                secondDotPosition = resolvedAlias.indexOf('.', firstDotPosition + 1);
            }
        }

        return resolvedAlias;
    }

    public static Object convertType(Class<?> rootType, String propertyName, Object originalValue) {
        if (rootType != null) {
            Class<?> propertyType = resolvePropertyType(rootType, propertyName);
            if (!Object.class.equals(propertyType)) {
                try {
                    return defaultConversionService.convert(originalValue, propertyType);
                } catch (Exception ex) {
                    return originalValue;
                }
            }
        }

        return originalValue;
    }

    private static Class<?> resolvePropertyType(Class<?> targetClass, String propertyName) {
        int dotPosition = propertyName.indexOf('.');
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

    private static Class<?> resolveDirectPropertyType(Class<?> targetClass, String propertyName) {
        return BeanUtils.findPropertyType(propertyName, targetClass);
    }

    private static Class<?> resolveCollectionType(Class<?> targetClass, String propertyName) {
        try {
            Field field = targetClass.getDeclaredField(propertyName);
            ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
            return (Class<?>) parameterizedType.getActualTypeArguments()[0];
        } catch (NoSuchFieldException | ClassCastException e) {
            return Object.class;
        }
    }
}
