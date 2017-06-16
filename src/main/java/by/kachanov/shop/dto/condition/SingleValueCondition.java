package by.kachanov.shop.dto.condition;

import java.util.Collection;
import java.util.Collections;

public abstract class SingleValueCondition extends AbstractCondition {

    protected String field;

    protected String value;

    public SingleValueCondition() {
    }

    public SingleValueCondition(String field, String value) {
        this.field = field;
        this.value = value;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public Collection<String> getFields() {
        return Collections.singletonList(field);
    }
}
