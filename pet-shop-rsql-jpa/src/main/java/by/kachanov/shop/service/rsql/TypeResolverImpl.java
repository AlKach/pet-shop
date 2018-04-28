package by.kachanov.shop.service.rsql;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;

import org.springframework.stereotype.Component;

@Component
public class TypeResolverImpl implements TypeResolver {
    @Override
    public Class<?> resolveType(Class<?> rootType, String path) {
        String[] pathParts = path.split("\\.");
        Class<?> currentType = rootType;
        for (String pathPart : pathParts) {
            try {
                Field field = findField(currentType, pathPart);
                Class<?> fieldType = field.getType();
                if (fieldType.isArray()) {
                    currentType = fieldType.getComponentType();
                } else if (Collection.class.isAssignableFrom(fieldType)) {
                    ParameterizedType genericType = (ParameterizedType) field.getGenericType();
                    currentType = (Class<?>) genericType.getActualTypeArguments()[0];
                } else {
                    currentType = fieldType;
                }
            } catch (NoSuchFieldException e) {
                throw new IllegalArgumentException("Field " + pathPart + " is not present in " + currentType.getCanonicalName());
            }
        }
        return currentType;
    }

    private Field findField(Class<?> rootType, String property) throws NoSuchFieldException {
        if (rootType.getSuperclass() == null) {
            throw new NoSuchFieldException();
        }

        try {
            return rootType.getDeclaredField(property);
        } catch (NoSuchFieldException e) {
            return findField(rootType.getSuperclass(), property);
        }
    }
}
