package by.kachanov.shop.dto.condition;

import java.util.List;

public abstract class MultiValueCondition implements Condition {

    protected String field;

    protected List<String> values;

    public MultiValueCondition() {
    }

    public MultiValueCondition(String field, List<String> values) {
        this.field = field;
        this.values = values;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }
}
