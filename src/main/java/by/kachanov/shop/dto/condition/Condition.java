package by.kachanov.shop.dto.condition;

import java.util.Collection;

public interface Condition {

    Collection<String> getFields();

    Condition withRootType(Class<?> rootType);

    Class<?> getRootType();

}
