package by.kachanov.shop.service.rsql;

public interface TypeResolver {

    Class<?> resolveType(Class<?> rootType, String path);

}
