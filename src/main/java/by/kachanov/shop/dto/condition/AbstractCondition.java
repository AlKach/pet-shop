package by.kachanov.shop.dto.condition;

public abstract class AbstractCondition implements Condition {

    private Class<?> rootType;

    @Override
    public Condition withRootType(Class<?> rootType) {
        this.rootType = rootType;
        return this;
    }

    @Override
    public Class<?> getRootType() {
        return rootType;
    }
}
